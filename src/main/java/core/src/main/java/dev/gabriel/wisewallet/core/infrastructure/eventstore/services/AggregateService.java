package dev.gabriel.wisewallet.core.infrastructure.eventstore.services;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.events.DomainEventWithId;
import dev.gabriel.wisewallet.core.infrastructure.exceptions.OptimisticConcurrencyControlException;
import dev.gabriel.wisewallet.core.infrastructure.exceptions.SnapshotNotFoundException;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.core.domain.models.AggregateFactory;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.configuration.EventStoreConfiguration;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.configuration.EventStoreConfiguration.SnapshottingConfiguration;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.repositories.AggregateRepository;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.repositories.EventRepository;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
public class AggregateService {
    private final AggregateRepository aggregateRepository;
    private final EventRepository eventRepository;
    private final EventStoreConfiguration eventStoreConfiguration;
    private final AggregateFactory aggregateFactory;

    public List<DomainEventWithId<DomainEvent>> save(Aggregate aggregate) {
        String aggregateType = aggregate.getAggregateType();
        UUID aggregateId = aggregate.getId().getValue();

        aggregateRepository.createIfNotExists(aggregateType, aggregateId);

        Long expectedVersion = aggregate.getBaseVersion();
        Long newVersion = aggregate.getVersion();

        if(!aggregateRepository.checkAndUpdateVersion(aggregateId, expectedVersion, newVersion))
            throw new OptimisticConcurrencyControlException(
                    "Optimistic concurrency control error in aggregate %s %s: current version doesn't match expected version %s"
                            .formatted(aggregateType, aggregateId, expectedVersion));

        SnapshottingConfiguration snapshottingConfiguration = eventStoreConfiguration.getSnapshotting(aggregateType);
        List<DomainEvent> changes = aggregate.getChanges();
        List<DomainEventWithId<DomainEvent>> newEvents = new ArrayList<>();

        for(DomainEvent event : changes) {
            DomainEventWithId<DomainEvent> newEvent = eventRepository.append(event);
            newEvents.add(newEvent);
            this.createSnapshot(snapshottingConfiguration, aggregate);
        }

        return newEvents;
    }

    private void createSnapshot(SnapshottingConfiguration snapshottingConfiguration, Aggregate aggregate) {
        if(snapshottingConfiguration.enabled() &&
           snapshottingConfiguration.nthEvent() > 1 &&
           aggregate.getVersion() % snapshottingConfiguration.nthEvent() == 0
        ) {
           aggregateRepository.createSnapshot(aggregate);
        }
    }

    private Aggregate getFromSnapshot(@NonNull UUID aggregateId, @Nullable Long version) {
        Optional<Aggregate> aggregate = aggregateRepository.getSnapshot(aggregateId, version);
        return aggregate.orElseThrow(() ->
                new SnapshotNotFoundException("Snapshot of %s with version %s was nott found.".formatted(aggregateId, version))
        );
    }

    private Aggregate getFromEventStream(String aggregateType, UUID aggregateId, @Nullable Long version) {
        List<DomainEvent> events = eventRepository
                .getEvents(aggregateId, null, version)
                .stream()
                .map(DomainEventWithId::event)
                .toList();

        Aggregate aggregate = aggregateFactory.create(aggregateType, aggregateId);
        aggregate.loadFromHistory(events);

        return aggregate;
    }

    public Aggregate get(@NonNull String aggregateType, @NonNull UUID aggregateId, @Nullable Long version) {
        SnapshottingConfiguration snapshottingConfiguration = eventStoreConfiguration.getSnapshotting(aggregateType);
        Aggregate aggregate;

        if(snapshottingConfiguration.enabled()) {
            try {
                aggregate = getFromSnapshot(aggregateId, version);
            }catch(SnapshotNotFoundException e) {
                return getFromEventStream(aggregateType, aggregateId, version);
            }
        }else {
            aggregate = getFromEventStream(aggregateType, aggregateId, version);
        }

        return aggregate;
    }

}

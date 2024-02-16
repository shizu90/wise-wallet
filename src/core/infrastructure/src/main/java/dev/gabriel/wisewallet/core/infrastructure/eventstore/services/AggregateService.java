package dev.gabriel.wisewallet.core.infrastructure.eventstore.services;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.events.DomainEventWithId;
import dev.gabriel.wisewallet.core.infrastructure.exceptions.OptimisticConcurrencyControlException;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.core.domain.models.AggregateFactory;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.configuration.EventStoreConfiguration;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.configuration.EventStoreConfiguration.SnapshottingConfiguration;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.repositories.AggregateRepository;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.repositories.EventRepository;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final Logger logger = LogManager.getLogger(AggregateService.class);

    public List<DomainEventWithId<DomainEvent>> save(Aggregate aggregate) {
        logger.info("Saving aggregate of type %s with version %s.".formatted(aggregate.getAggregateType(), aggregate.getVersion()));

        String aggregateType = aggregate.getAggregateType();
        UUID aggregateId = aggregate.getId();

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
            logger.info("Creating snapshot of type %s with version %s.".formatted(aggregate.getAggregateType(), aggregate.getVersion()));
            aggregateRepository.createSnapshot(aggregate);
        }
    }

    private Optional<Aggregate> loadFromSnapshot(@NonNull UUID aggregateId, @Nullable Long version) {
        return aggregateRepository.getSnapshot(aggregateId, version)
                .map(aggregate -> {
                    Long snapshotVersion = aggregate.getVersion();
                    if(version == null || snapshotVersion < version) {
                        List<DomainEvent> events = eventRepository
                                .getEvents(aggregateId, snapshotVersion, version)
                                .stream()
                                .map(DomainEventWithId::event)
                                .toList();
                        aggregate.loadFromHistory(events);
                    }

                    return aggregate;
                });
    }

    private Aggregate loadFromEventStream(String aggregateType, UUID aggregateId, @Nullable Long version) {
        List<DomainEvent> events = eventRepository
                .getEvents(aggregateId, null, version)
                .stream()
                .map(DomainEventWithId::event)
                .toList();

        if(events.isEmpty()) return null;

        Aggregate aggregate = aggregateFactory.create(aggregateType, aggregateId, 0L);
        aggregate.loadFromHistory(events);

        return aggregate;
    }

    public Aggregate load(@NonNull String aggregateType, @NonNull UUID aggregateId, @Nullable Long version) {
        SnapshottingConfiguration snapshottingConfiguration = eventStoreConfiguration.getSnapshotting(aggregateType);
        Aggregate aggregate = null;

        if(snapshottingConfiguration.enabled()) {
            aggregate = loadFromSnapshot(aggregateId, version).orElseGet(() -> {
                return this.loadFromEventStream(aggregateType, aggregateId, version);
            });
        }else {
            aggregate = loadFromEventStream(aggregateType, aggregateId, version);
        }

        return aggregate;
    }

}

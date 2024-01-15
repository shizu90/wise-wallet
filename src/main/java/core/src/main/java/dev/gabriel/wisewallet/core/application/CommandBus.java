package dev.gabriel.wisewallet.core.application;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.events.DomainEventWithId;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.core.domain.models.AggregateFactory;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.services.AggregateService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CommandBus<T extends Command> {
    private final List<CommandHandler<T>> commandHandlers;
    private final EventHandler eventHandler;
    private final AggregateService aggregateService;
    private final AggregateFactory aggregateFactory;

    public Aggregate execute(@NonNull T command) {
        String aggregateType = command.getAggregateType();
        UUID aggregateId = command.getAggregateId();

        Aggregate aggregate;
        if(aggregateId == null) {
            aggregate = aggregateFactory.create(aggregateType, UUID.randomUUID());
        }else {
            aggregate = aggregateService.get(aggregateType, aggregateId, null);
        }

        commandHandlers
                .stream()
                .filter(commandHandler -> commandHandler.getCommandType().equals(command.getClass()))
                .findFirst()
                .ifPresentOrElse(commandHandler -> {
                    commandHandler.handle(aggregate, command);
                }, () -> {
                    throw new UnsupportedOperationException();
                });

        List<DomainEventWithId<DomainEvent>> newEvents = aggregateService.save(aggregate);

        eventHandler.handleEvents(newEvents, aggregate);

        return aggregate;
    }
}

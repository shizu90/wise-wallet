package dev.gabriel.wisewallet.core.application;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.core.infrastructure.services.EventPublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CommandBus<T extends Command> {
    private final List<CommandHandler<T>> commandHandlers;
    private final EventPublisher eventPublisher;
    private final SyncEventHandler eventHandler;
    private final Logger logger = LogManager.getLogger(CommandBus.class);

    private CommandHandler<T> findHandler(Class<? extends Command> commandType) {
        return commandHandlers
                .stream()
                .filter(h -> h.getCommandType().equals(commandType))
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);
    }

    public Aggregate execute(@NonNull T command) {
        CommandHandler<T> commandHandler = findHandler(command.getClass());

        try {
            logger.info("Executing %s.".formatted(command.getClass().getSimpleName()));

            Aggregate result = commandHandler.handle(command);
            eventHandler.handleEvents(result);

            for(DomainEvent event : result.getChanges()) {
                eventPublisher.publish(event.getClass().getSimpleName(), event);
            }

            return result;
        }catch(DomainException e) {
            logger.warn(e.getMessage());
            throw e;
        }
    }
}

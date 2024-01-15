package dev.gabriel.wisewallet.core.application;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final SyncEventHandler eventHandler;

    public Aggregate execute(@NonNull T command) {
        CommandHandler<T> commandHandler = commandHandlers
                .stream()
                .filter(h -> h.getCommandType().equals(command.getClass()))
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);

        Aggregate aggregate = commandHandler.handle(command);
        eventHandler.handleEvents(aggregate);

        return aggregate;
    }
}

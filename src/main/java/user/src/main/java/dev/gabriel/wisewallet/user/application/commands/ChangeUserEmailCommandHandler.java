package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.commands.ChangeUserEmailCommand;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ChangeUserEmailCommandHandler implements CommandHandler<ChangeUserEmailCommand> {
    @Override
    public void handle(Aggregate aggregate, ChangeUserEmailCommand command) {
        aggregate.process(command);
    }

    @Override
    @NonNull
    public Class<ChangeUserEmailCommand> getCommandType() {
        return ChangeUserEmailCommand.class;
    }
}

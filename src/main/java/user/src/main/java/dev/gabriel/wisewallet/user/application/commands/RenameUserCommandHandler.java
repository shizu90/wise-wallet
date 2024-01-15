package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.commands.RenameUserCommand;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RenameUserCommandHandler implements CommandHandler<RenameUserCommand> {
    @Override
    public void handle(Aggregate aggregate, RenameUserCommand command) {
        aggregate.process(command);
    }

    @Override
    @NonNull
    public Class<RenameUserCommand> getCommandType() {
        return RenameUserCommand.class;
    }
}

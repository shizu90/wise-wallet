package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.commands.DeleteUserCommand;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand> {
    @Override
    public void handle(Aggregate aggregate, DeleteUserCommand command) {
        aggregate.process(command);
    }

    @Override
    @NonNull
    public Class<DeleteUserCommand> getCommandType() {
        return DeleteUserCommand.class;
    }
}

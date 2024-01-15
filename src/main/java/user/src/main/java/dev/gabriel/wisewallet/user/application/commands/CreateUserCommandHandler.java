package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.commands.CreateUserCommand;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {
    @Override
    public void handle(Aggregate aggregate, CreateUserCommand command) {
        aggregate.process(command);
    }

    @Override
    @NonNull
    public Class<CreateUserCommand> getCommandType() {
        return CreateUserCommand.class;
    }
}

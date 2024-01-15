package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.commands.ChangeUserPasswordCommand;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ChangeUserPasswordCommandHandler implements CommandHandler<ChangeUserPasswordCommand> {
    @Override
    public void handle(Aggregate aggregate, ChangeUserPasswordCommand command) {
        aggregate.process(command);
    }

    @Override
    @NonNull
    public Class<ChangeUserPasswordCommand> getCommandType() {
        return ChangeUserPasswordCommand.class;
    }
}

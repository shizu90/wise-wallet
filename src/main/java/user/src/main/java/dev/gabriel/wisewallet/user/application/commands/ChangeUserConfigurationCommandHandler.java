package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.commands.ChangeUserConfigurationCommand;
import lombok.NonNull;

public class ChangeUserConfigurationCommandHandler implements CommandHandler<ChangeUserConfigurationCommand> {
    @Override
    public void handle(Aggregate aggregate, ChangeUserConfigurationCommand command) {
        aggregate.process(command);
    }

    @Override
    @NonNull
    public Class<ChangeUserConfigurationCommand> getCommandType() {
        return ChangeUserConfigurationCommand.class;
    }
}

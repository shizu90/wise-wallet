package dev.gabriel.wisewallet.core.application;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;

import lombok.NonNull;

public interface CommandHandler<C extends Command> {
    Aggregate handle(@NonNull C command);

    @NonNull
    Class<C> getCommandType();
}

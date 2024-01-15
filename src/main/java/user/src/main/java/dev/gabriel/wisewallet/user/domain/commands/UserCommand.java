package dev.gabriel.wisewallet.user.domain.commands;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.user.domain.models.AggregateType;

import java.util.UUID;

public abstract class UserCommand extends Command {
    protected UserCommand(UUID aggregateId) {
        super(aggregateId, AggregateType.USER.toString());
    }
}

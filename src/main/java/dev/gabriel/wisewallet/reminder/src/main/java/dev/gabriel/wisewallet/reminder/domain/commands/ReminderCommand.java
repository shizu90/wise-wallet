package dev.gabriel.wisewallet.reminder.domain.commands;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.reminder.domain.models.AggregateType;

import java.util.UUID;

public abstract class ReminderCommand extends Command {
    protected ReminderCommand(UUID id) {
        super(id, AggregateType.REMINDER.toString());
    }
}

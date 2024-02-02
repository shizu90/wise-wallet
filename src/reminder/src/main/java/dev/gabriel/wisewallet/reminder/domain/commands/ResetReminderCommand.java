package dev.gabriel.wisewallet.reminder.domain.commands;

import java.util.UUID;

public class ResetReminderCommand extends ReminderCommand {
    public ResetReminderCommand(UUID id) {
        super(id);
    }
}

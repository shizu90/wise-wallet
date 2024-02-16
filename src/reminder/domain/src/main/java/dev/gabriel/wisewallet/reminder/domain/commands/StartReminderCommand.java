package dev.gabriel.wisewallet.reminder.domain.commands;

import java.util.UUID;

public class StartReminderCommand extends ReminderCommand {
    public StartReminderCommand(UUID id) {
        super(id);
    }
}

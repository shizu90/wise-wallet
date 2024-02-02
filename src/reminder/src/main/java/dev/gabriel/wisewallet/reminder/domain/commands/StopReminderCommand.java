package dev.gabriel.wisewallet.reminder.domain.commands;

import java.util.UUID;

public class StopReminderCommand extends ReminderCommand {
    public StopReminderCommand(UUID id) {
        super(id);
    }
}

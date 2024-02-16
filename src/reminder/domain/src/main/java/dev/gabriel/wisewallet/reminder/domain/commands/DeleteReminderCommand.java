package dev.gabriel.wisewallet.reminder.domain.commands;

import java.util.UUID;

public class DeleteReminderCommand extends ReminderCommand {
    public DeleteReminderCommand(UUID id) {
        super(id);
    }
}

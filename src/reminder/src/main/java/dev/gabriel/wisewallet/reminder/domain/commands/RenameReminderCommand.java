package dev.gabriel.wisewallet.reminder.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RenameReminderCommand extends ReminderCommand {
    private final String name;

    public RenameReminderCommand(UUID id, String name) {
        super(id);
        this.name = name;
    }
}

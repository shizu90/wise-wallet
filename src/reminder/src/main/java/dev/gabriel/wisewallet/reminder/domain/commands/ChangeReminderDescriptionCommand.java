package dev.gabriel.wisewallet.reminder.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeReminderDescriptionCommand extends ReminderCommand {
    private final String description;

    public ChangeReminderDescriptionCommand(UUID id, String description) {
        super(id);
        this.description = description;
    }
}

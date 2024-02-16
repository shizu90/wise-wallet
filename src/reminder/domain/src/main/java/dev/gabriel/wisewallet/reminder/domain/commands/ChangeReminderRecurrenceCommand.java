package dev.gabriel.wisewallet.reminder.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeReminderRecurrenceCommand extends ReminderCommand{
    private final Long recurrence;

    public ChangeReminderRecurrenceCommand(UUID id, Long recurrence) {
        super(id);
        this.recurrence = recurrence;
    }
}

package dev.gabriel.wisewallet.reminder.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateReminderCommand extends ReminderCommand {
    private final String name;
    private final String description;
    private final Long recurrence;
    private final Long maxRuns;
    private final UUID userId;

    public CreateReminderCommand(UUID id,
                                 String name,
                                 String description,
                                 Long recurrence,
                                 Long maxRuns,
                                 UUID userId
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.recurrence = recurrence;
        this.maxRuns = maxRuns;
        this.userId = userId;
    }
}

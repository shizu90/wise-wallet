package dev.gabriel.wisewallet.reminder.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ExecuteReminderCommand extends ReminderCommand {
    private final Long numberOfTimes;

    public ExecuteReminderCommand(UUID id, Long numberOfTimes) {
        super(id);
        this.numberOfTimes = numberOfTimes;
    }
}

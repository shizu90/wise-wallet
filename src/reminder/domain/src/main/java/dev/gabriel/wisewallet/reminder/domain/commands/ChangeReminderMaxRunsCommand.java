package dev.gabriel.wisewallet.reminder.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeReminderMaxRunsCommand extends ReminderCommand {
    private final Long maxRuns;

    public ChangeReminderMaxRunsCommand(UUID id, Long maxRuns) {
        super(id);
        this.maxRuns = maxRuns;
    }
}

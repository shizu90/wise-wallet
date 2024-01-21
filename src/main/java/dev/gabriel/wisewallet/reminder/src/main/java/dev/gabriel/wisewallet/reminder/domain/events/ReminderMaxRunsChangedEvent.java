package dev.gabriel.wisewallet.reminder.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class ReminderMaxRunsChangedEvent extends ReminderEvent {
    private final Long maxRuns;

    public ReminderMaxRunsChangedEvent(UUID id, Long version, Long maxRuns) {
        super(id, version);
        this.maxRuns = maxRuns;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.REMINDER_MAX_RUNS_CHANGED.toString();
    }
}

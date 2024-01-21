package dev.gabriel.wisewallet.reminder.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class ReminderExecutedEvent extends ReminderEvent {
    private final Long currentRuns;
    private final LocalDate lastRun;

    public ReminderExecutedEvent(UUID id, Long version, Long currentRuns, LocalDate lastRun) {
        super(id, version);
        this.currentRuns = currentRuns;
        this.lastRun = lastRun;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.REMINDER_EXECUTED.toString();
    }
}

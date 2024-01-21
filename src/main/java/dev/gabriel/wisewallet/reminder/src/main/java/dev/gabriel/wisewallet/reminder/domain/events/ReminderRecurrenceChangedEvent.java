package dev.gabriel.wisewallet.reminder.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class ReminderRecurrenceChangedEvent extends ReminderEvent {
    private final Long recurrence;

    public ReminderRecurrenceChangedEvent(UUID id, Long version, Long recurrence) {
        super(id, version);
        this.recurrence = recurrence;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.REMINDER_RECURRENCE_CHANGED.toString();
    }
}

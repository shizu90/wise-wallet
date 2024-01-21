package dev.gabriel.wisewallet.reminder.domain.events;

import lombok.NonNull;

import java.util.UUID;

public class ReminderStartedEvent extends ReminderEvent {
    public ReminderStartedEvent(UUID id, Long version) {
        super(id, version);
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.REMINDER_STARTED.toString();
    }
}

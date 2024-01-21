package dev.gabriel.wisewallet.reminder.domain.events;

import lombok.NonNull;

import java.util.UUID;

public class ReminderDeletedEvent extends ReminderEvent {
    public ReminderDeletedEvent(UUID id, Long version) {
        super(id, version);
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.REMINDER_DELETED.toString();
    }
}

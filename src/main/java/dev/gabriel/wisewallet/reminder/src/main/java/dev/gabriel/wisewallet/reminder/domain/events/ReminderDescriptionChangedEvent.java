package dev.gabriel.wisewallet.reminder.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class ReminderDescriptionChangedEvent extends ReminderEvent {
    private final String description;

    public ReminderDescriptionChangedEvent(UUID id, Long version, String description) {
        super(id, version);
        this.description = description;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.REMINDER_DESCRIPTION_CHANGED.toString();
    }
}

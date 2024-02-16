package dev.gabriel.wisewallet.reminder.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class ReminderRenamedEvent extends ReminderEvent {
    private final String name;

    public ReminderRenamedEvent(UUID id, Long version, String name) {
        super(id, version);
        this.name = name;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.REMINDER_RENAMED.toString();
    }
}

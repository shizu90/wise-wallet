package dev.gabriel.wisewallet.reminder.domain.events;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

import java.util.UUID;

public abstract class ReminderEvent extends DomainEvent {
    protected ReminderEvent(UUID id, Long version) {
        super(id, version);
    }
}

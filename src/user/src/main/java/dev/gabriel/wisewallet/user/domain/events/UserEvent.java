package dev.gabriel.wisewallet.user.domain.events;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

import java.util.UUID;

public abstract class UserEvent extends DomainEvent {
    protected UserEvent(UUID aggregateId, Long version) {
        super(aggregateId, version);
    }
}

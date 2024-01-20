package dev.gabriel.wisewallet.category.domain.events;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

import java.util.UUID;

public abstract class CategoryEvent extends DomainEvent {
    protected CategoryEvent(UUID id, Long version) {
        super(id, version);
    }
}

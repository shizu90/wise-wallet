package dev.gabriel.wisewallet.budget.domain.events;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

import java.util.UUID;

public abstract class BudgetEvent extends DomainEvent {
    protected BudgetEvent(UUID id, Long version) {
        super(id, version);
    }
}

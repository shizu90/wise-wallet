package dev.gabriel.wisewallet.recurringbill.domain.events;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

import java.util.UUID;

public abstract class RecurringBillEvent extends DomainEvent {
    protected RecurringBillEvent(UUID id, Long version) {
        super(id, version);
    }
}

package dev.gabriel.wisewallet.bill.domain.events;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

import java.util.UUID;

public abstract class BillEvent extends DomainEvent {
    protected BillEvent(UUID aggregateId, Long version) {
        super(aggregateId, version);
    }
}

package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.NonNull;

import java.util.UUID;

public class RecurringBillDeletedEvent extends RecurringBillEvent {
    public RecurringBillDeletedEvent(UUID id, Long version) {
        super(id, version);
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_DELETED.toString();
    }
}

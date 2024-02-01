package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.NonNull;

import java.util.UUID;

public class RecurringBillResetEvent extends RecurringBillEvent {
    public RecurringBillResetEvent(UUID id, Long version) {
        super(id, version);
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_RESET.toString();
    }
}

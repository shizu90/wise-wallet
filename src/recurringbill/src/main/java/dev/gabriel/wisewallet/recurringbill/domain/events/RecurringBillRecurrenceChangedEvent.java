package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillRecurrenceChangedEvent extends RecurringBillEvent {
    private final Long recurrence;

    public RecurringBillRecurrenceChangedEvent(UUID id, Long version, Long recurrence) {
        super(id, version);
        this.recurrence = recurrence;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_RECURRENCE_CHANGED.toString();
    }
}

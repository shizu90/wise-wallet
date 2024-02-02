package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillPeriodExecutedEvent extends RecurringBillEvent {
    private final Long periods;

    public RecurringBillPeriodExecutedEvent(UUID id, Long version, Long periods) {
        super(id, version);
        this.periods = periods;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_PERIOD_EXECUTED.toString();
    }
}

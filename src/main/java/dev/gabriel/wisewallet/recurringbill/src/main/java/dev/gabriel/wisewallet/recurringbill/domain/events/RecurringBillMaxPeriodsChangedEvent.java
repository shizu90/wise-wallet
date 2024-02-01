package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillMaxPeriodsChangedEvent extends RecurringBillEvent {
    private final Long maxPeriods;

    public RecurringBillMaxPeriodsChangedEvent(UUID id, Long version, Long maxPeriods) {
        super(id, version);
        this.maxPeriods = maxPeriods;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_MAX_PERIODS_CHANGED.toString();
    }
}

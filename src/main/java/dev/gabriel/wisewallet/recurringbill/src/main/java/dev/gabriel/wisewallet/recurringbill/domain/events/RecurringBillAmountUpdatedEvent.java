package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class RecurringBillAmountUpdatedEvent extends RecurringBillEvent {
    private final BigDecimal amount;

    public RecurringBillAmountUpdatedEvent(UUID id, Long version, BigDecimal amount) {
        super(id, version);
        this.amount = amount;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_AMOUNT_UPDATED.toString();
    }
}

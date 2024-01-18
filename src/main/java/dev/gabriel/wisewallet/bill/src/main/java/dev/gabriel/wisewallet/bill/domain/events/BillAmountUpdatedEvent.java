package dev.gabriel.wisewallet.bill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class BillAmountUpdatedEvent extends BillEvent {
    private final BigDecimal amount;

    public BillAmountUpdatedEvent(UUID id, Long version, BigDecimal amount) {
        super(id, version);
        this.amount = amount;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BILL_AMOUNT_UPDATED.toString();
    }
}

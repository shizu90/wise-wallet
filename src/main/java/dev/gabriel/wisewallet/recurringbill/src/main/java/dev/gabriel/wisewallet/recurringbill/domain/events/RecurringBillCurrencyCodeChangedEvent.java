package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillCurrencyCodeChangedEvent extends RecurringBillEvent {
    private final String currencyCode;

    public RecurringBillCurrencyCodeChangedEvent(UUID id, Long version, String currencyCode) {
        super(id, version);
        this.currencyCode = currencyCode;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_CURRENCY_CODE_CHANGED.toString();
    }
}

package dev.gabriel.wisewallet.bill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BillCurrencyCodeChangedEvent extends BillEvent {
    private final String currencyCode;

    public BillCurrencyCodeChangedEvent(UUID id, Long version, String currencyCode) {
        super(id, version);
        this.currencyCode = currencyCode;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BILL_CURRENCY_CODE_CHANGED.toString();
    }
}

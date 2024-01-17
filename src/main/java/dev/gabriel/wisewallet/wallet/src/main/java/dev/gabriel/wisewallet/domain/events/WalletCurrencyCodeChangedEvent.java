package dev.gabriel.wisewallet.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class WalletCurrencyCodeChangedEvent extends WalletEvent {
    private final String currencyCode;

    public WalletCurrencyCodeChangedEvent(UUID id, Long version, String currencyCode) {
        super(id, version);
        this.currencyCode = currencyCode;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.WALLET_CURRENCY_CODE_CHANGED.toString();
    }
}

package dev.gabriel.wisewallet.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class WalletBalanceUpdatedEvent extends WalletEvent{
    private final BigDecimal balance;

    public WalletBalanceUpdatedEvent(UUID id, Long version, BigDecimal balance) {
        super(id, version);
        this.balance = balance;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.WALLET_BALANCE_UPDATED.toString();
    }
}

package dev.gabriel.wisewallet.bill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BillWalletChangedEvent extends BillEvent {
    private final UUID walletId;

    public BillWalletChangedEvent(UUID id, Long version, UUID walletId) {
        super(id, version);
        this.walletId = walletId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BILL_WALLET_CHANGED.toString();
    }
}

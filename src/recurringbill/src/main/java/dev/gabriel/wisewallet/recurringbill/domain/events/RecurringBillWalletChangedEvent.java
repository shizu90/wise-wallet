package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillWalletChangedEvent extends RecurringBillEvent {
    private final UUID walletId;

    public RecurringBillWalletChangedEvent(UUID id, Long version, UUID walletId) {
        super(id, version);
        this.walletId = walletId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_WALLET_CHANGED.toString();
    }
}

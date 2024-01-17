package dev.gabriel.wisewallet.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class WalletDeletedEvent extends WalletEvent {
    public WalletDeletedEvent(UUID id, Long version) {
        super(id, version);
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.WALLET_DELETED.toString();
    }
}

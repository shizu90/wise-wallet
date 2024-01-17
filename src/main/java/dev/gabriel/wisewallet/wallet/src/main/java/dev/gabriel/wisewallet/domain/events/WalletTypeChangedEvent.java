package dev.gabriel.wisewallet.domain.events;

import dev.gabriel.wisewallet.domain.models.WalletType;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class WalletTypeChangedEvent extends WalletEvent {
    private final WalletType type;

    public WalletTypeChangedEvent(UUID id, Long version, WalletType type) {
        super(id, version);
        this.type = type;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.WALLET_TYPE_CHANGED.toString();
    }
}
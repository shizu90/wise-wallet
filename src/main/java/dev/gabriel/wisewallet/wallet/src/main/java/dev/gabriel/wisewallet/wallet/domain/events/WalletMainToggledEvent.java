package dev.gabriel.wisewallet.wallet.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class WalletMainToggledEvent extends WalletEvent {
    private final Boolean main;

    public WalletMainToggledEvent(UUID id, Long version, Boolean main) {
        super(id, version);
        this.main = main;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.WALLET_MAIN_TOGGLED.toString();
    }
}

package dev.gabriel.wisewallet.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class WalletRenamedEvent extends WalletEvent {
    private final String name;

    public WalletRenamedEvent(UUID id, Long version, String name) {
        super(id, version);
        this.name = name;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.WALLET_RENAMED.toString();
    }
}

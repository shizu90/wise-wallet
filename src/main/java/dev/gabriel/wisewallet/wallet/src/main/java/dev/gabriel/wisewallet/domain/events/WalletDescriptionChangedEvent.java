package dev.gabriel.wisewallet.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class WalletDescriptionChangedEvent extends WalletEvent {
    private final String description;

    public WalletDescriptionChangedEvent(UUID id, Long version, String description) {
        super(id, version);
        this.description = description;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.WALLET_DESCRIPTION_CHANGED.toString();
    }
}

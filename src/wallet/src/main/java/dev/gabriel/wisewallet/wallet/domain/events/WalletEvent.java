package dev.gabriel.wisewallet.wallet.domain.events;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

import java.util.UUID;

public abstract class WalletEvent extends DomainEvent {
    protected WalletEvent(UUID id, Long version) {
        super(id, version);
    }
}

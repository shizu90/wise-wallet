package dev.gabriel.wisewallet.domain.models;

import dev.gabriel.wisewallet.core.domain.models.Identity;

import java.util.UUID;

public class WalletId extends Identity {
    private WalletId(UUID id) {
        super(id);
    }

    public static WalletId create(UUID id) {
        return new WalletId(id);
    }
}

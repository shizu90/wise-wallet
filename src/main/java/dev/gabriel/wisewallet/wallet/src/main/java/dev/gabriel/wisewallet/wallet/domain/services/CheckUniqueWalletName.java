package dev.gabriel.wisewallet.wallet.domain.services;

import java.util.UUID;

public interface CheckUniqueWalletName {
    long exists(UUID userId, String name);
}

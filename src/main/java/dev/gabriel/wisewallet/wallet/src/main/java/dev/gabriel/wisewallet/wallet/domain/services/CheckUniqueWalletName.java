package dev.gabriel.wisewallet.wallet.domain.services;

import java.util.UUID;

public interface CheckUniqueWalletName {
    boolean exists(String name, UUID userId);
}

package dev.gabriel.wisewallet.wallet.domain.services;

import dev.gabriel.wisewallet.core.domain.services.DomainService;

import java.util.UUID;

public interface CheckUserWallets extends DomainService {
    int getSize(UUID userId);
}

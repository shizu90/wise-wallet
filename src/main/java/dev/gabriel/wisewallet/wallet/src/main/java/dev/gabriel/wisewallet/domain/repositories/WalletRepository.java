package dev.gabriel.wisewallet.domain.repositories;

import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import dev.gabriel.wisewallet.domain.models.Wallet;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends DomainRepository {
    Optional<Wallet> load(UUID walletId);
    void saveChanges(Wallet wallet);
}

package dev.gabriel.wisewallet.wallet.domain.repositories;

import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends DomainRepository {
    Optional<Wallet> load(UUID id);
    void saveChanges(Wallet wallet);
}

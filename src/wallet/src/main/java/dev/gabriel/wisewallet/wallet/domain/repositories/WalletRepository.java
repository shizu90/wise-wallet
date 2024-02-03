package dev.gabriel.wisewallet.wallet.domain.repositories;

import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends DomainRepository {
    Optional<Wallet> load(@NonNull UUID id);
    Optional<Wallet> load(@NonNull UUID id, Long version);
    void saveChanges(@NonNull Wallet wallet);
}

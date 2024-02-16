package dev.gabriel.wisewallet.wallet.domain.repositories;

import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import jakarta.annotation.Nullable;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends DomainRepository {
    Optional<Wallet> load(@NonNull UUID id, @Nullable Long version);
    void saveChanges(@NonNull Wallet wallet);
}

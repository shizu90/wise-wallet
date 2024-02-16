package dev.gabriel.wisewallet.wallet.infrastructure.eventstore;

import dev.gabriel.wisewallet.core.infrastructure.eventstore.services.AggregateService;
import dev.gabriel.wisewallet.wallet.domain.models.AggregateType;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletEventStore implements WalletRepository {
    private final AggregateService aggregateService;

    @Override
    public Optional<Wallet> load(@NonNull UUID walletId, @Nullable Long version) {
        Wallet wallet = (Wallet) aggregateService.load(AggregateType.WALLET.toString(), walletId, version);
        return Optional.ofNullable(wallet);
    }

    @Override
    public void saveChanges(@NonNull Wallet wallet) {
        aggregateService.save(wallet);
    }
}

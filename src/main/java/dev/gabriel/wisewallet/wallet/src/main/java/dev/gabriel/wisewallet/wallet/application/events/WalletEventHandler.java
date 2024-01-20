package dev.gabriel.wisewallet.wallet.application.events;

import dev.gabriel.wisewallet.core.application.SyncEventHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.wallet.domain.models.AggregateType;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjection;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjectionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletEventHandler implements SyncEventHandler {
    private final WalletProjectionRepository projectionRepository;

    @Override
    public void handleEvents(Aggregate aggregate) {
        Wallet wallet = (Wallet) aggregate;
        WalletProjection walletProjection = WalletProjection.create(
                wallet.getId().getValue(),
                wallet.getName().getValue(),
                wallet.getDescription().getValue(),
                wallet.getBalance().getValue(),
                wallet.getInitialBalance().getValue(),
                wallet.getBalance().getCurrencyCode(),
                wallet.getMain(),
                wallet.getType(),
                wallet.getUserId(),
                wallet.getIsDeleted()
        );

        projectionRepository.save(walletProjection);
    }

    @Override
    @NonNull
    public String getAggregateType() {
        return AggregateType.WALLET.toString();
    }
}
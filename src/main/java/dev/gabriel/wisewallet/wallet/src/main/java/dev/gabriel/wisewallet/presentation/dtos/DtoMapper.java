package dev.gabriel.wisewallet.presentation.dtos;

import dev.gabriel.wisewallet.domain.models.Wallet;
import dev.gabriel.wisewallet.infrastructure.projection.WalletProjection;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    public WalletResponseDto toResponseDto(@Nullable Wallet wallet) {
        if(wallet == null) return null;
        return new WalletResponseDto(
                wallet.getId().getValue(),
                wallet.getName().getValue(),
                wallet.getDescription().getValue(),
                wallet.getBalance().getValue(),
                wallet.getInitialBalance().getValue(),
                wallet.getBalance().getCurrencyCode(),
                wallet.getMain(),
                wallet.getType(),
                wallet.getUserId()
        );
    }

    public WalletResponseDto toResponseDto(@Nullable WalletProjection walletProjection) {
        if(walletProjection == null) return null;
        return new WalletResponseDto(
                walletProjection.getId(),
                walletProjection.getName(),
                walletProjection.getDescription(),
                walletProjection.getBalance(),
                walletProjection.getInitialBalance(),
                walletProjection.getCurrencyCode(),
                walletProjection.getMain(),
                walletProjection.getType(),
                walletProjection.getUserId()
        );
    }
}

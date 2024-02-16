package dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.mappers;

import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjection;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.WalletListResponseDto;
import dev.gabriel.wisewallet.wallet.infrastructure.services.dtos.WalletResponseDto;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class WalletDtoMapper {
    public WalletResponseDto toResponseDto(@Nullable Wallet wallet) {
        if(wallet == null) return null;
        return new WalletResponseDto(
                wallet.getId(),
                wallet.getName().getValue(),
                wallet.getDescription().getValue(),
                wallet.getBalance().getValue(),
                wallet.getBalance().getCurrencyCode(),
                wallet.getType(),
                wallet.getUserId(),
                wallet.getCreatedAt(),
                wallet.getUpdatedAt()
        );
    }

    public WalletResponseDto toResponseDto(@Nullable WalletProjection walletProjection) {
        if(walletProjection == null) return null;
        return new WalletResponseDto(
                walletProjection.getId(),
                walletProjection.getName(),
                walletProjection.getDescription(),
                walletProjection.getBalance(),
                walletProjection.getCurrencyCode(),
                walletProjection.getType(),
                walletProjection.getUserId(),
                walletProjection.getCreatedAt(),
                walletProjection.getUpdatedAt()
        );
    }

    public WalletListResponseDto toResponseDto(@Nullable Page<WalletProjection> walletProjectionList) {
        if(walletProjectionList == null) return null;
        return new WalletListResponseDto(
                walletProjectionList.getContent().stream().map(this::toResponseDto).toList(),
                walletProjectionList.getTotalElements(),
                walletProjectionList.getTotalPages()
        );
    }
}

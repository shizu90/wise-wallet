package dev.gabriel.wisewallet.wallet.infrastructure.services.dtos;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletRequestDto(
        UUID id,
        String name,
        String description,
        BigDecimal balance,
        String currencyCode,
        WalletType type,
        UUID userId
) {}

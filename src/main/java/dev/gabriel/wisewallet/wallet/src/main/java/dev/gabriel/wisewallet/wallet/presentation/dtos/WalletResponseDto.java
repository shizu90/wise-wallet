package dev.gabriel.wisewallet.wallet.presentation.dtos;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record WalletResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal balance,
        BigDecimal initialBalance,
        String currencyCode,
        Boolean main,
        WalletType type,
        UUID userId,
        Instant createdAt,
        Instant updatedAt
) {}

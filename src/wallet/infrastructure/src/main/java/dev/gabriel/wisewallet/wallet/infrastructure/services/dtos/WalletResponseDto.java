package dev.gabriel.wisewallet.wallet.infrastructure.services.dtos;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record WalletResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal balance,
        String currencyCode,
        WalletType type,
        UUID userId,
        Instant createdAt,
        Instant updatedAt
) {}

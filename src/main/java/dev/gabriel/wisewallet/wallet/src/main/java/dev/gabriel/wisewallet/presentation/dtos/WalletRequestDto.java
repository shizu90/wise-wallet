package dev.gabriel.wisewallet.presentation.dtos;

import dev.gabriel.wisewallet.domain.models.WalletType;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletRequestDto(
        UUID id,
        String name,
        String description,
        BigDecimal balance,
        String currencyCode,
        Boolean main,
        WalletType type,
        UUID userId
) {}

package dev.gabriel.wisewallet.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.gabriel.wisewallet.domain.models.WalletType;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletResponseDto(
        @JsonProperty("walletId") UUID id,
        String name,
        String description,
        BigDecimal balance,
        BigDecimal initialBalance,
        String currencyCode,
        Boolean main,
        WalletType type,
        UUID userId
) {}

package dev.gabriel.wisewallet.bill.presentation.dtos;

import dev.gabriel.wisewallet.bill.domain.models.BillType;

import java.math.BigDecimal;
import java.util.UUID;

public record BillRequestDto(
        UUID id,
        String name,
        String description,
        BigDecimal amount,
        String currencyCode,
        BillType type,
        UUID walletId,
        UUID categoryId
) {}

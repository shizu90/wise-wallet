package dev.gabriel.wisewallet.bill.infrastructure.services.dtos;

import dev.gabriel.wisewallet.bill.domain.models.BillType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record BillResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal amount,
        String currencyCode,
        BillType type,
        UUID walletId,
        UUID categoryId,
        Instant createdAt,
        Instant updatedAt
) {}

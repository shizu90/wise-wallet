package dev.gabriel.wisewallet.budget.infrastructure.services.dtos;

import dev.gabriel.wisewallet.budget.domain.models.BudgetItem;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record BudgetResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal amount,
        String currencyCode,
        List<BudgetItem> items,
        UUID userId,
        Instant createdAt,
        Instant updatedAt
) {}

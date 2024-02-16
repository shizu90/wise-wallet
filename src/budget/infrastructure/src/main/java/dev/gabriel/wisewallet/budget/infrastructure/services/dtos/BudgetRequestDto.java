package dev.gabriel.wisewallet.budget.infrastructure.services.dtos;

import java.util.UUID;

public record BudgetRequestDto(
        UUID id,
        String name,
        String description,
        String currencyCode,
        UUID userId
) {}

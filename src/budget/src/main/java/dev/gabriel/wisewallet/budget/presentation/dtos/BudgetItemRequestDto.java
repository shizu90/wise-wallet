package dev.gabriel.wisewallet.budget.presentation.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record BudgetItemRequestDto(
        UUID billId,
        String name,
        BigDecimal amount,
        String currencyCode,
        String type
) {}

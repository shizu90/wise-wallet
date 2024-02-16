package dev.gabriel.wisewallet.budget.infrastructure.services.dtos;

import java.util.List;

public record BudgetListResponseDto(
        List<BudgetResponseDto> budgets,
        long totalElements,
        long totalPages
) {}

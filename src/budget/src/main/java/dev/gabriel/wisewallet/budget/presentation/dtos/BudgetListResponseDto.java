package dev.gabriel.wisewallet.budget.presentation.dtos;

import java.util.List;

public record BudgetListResponseDto(
        List<BudgetResponseDto> budgets,
        long totalElements,
        long totalPages
) {}

package dev.gabriel.wisewallet.budget.infrastructure.services.dtos.mapper;

import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.infrastructure.projection.BudgetProjection;
import dev.gabriel.wisewallet.budget.infrastructure.services.dtos.BudgetListResponseDto;
import dev.gabriel.wisewallet.budget.infrastructure.services.dtos.BudgetResponseDto;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BudgetDtoMapper {
    public BudgetResponseDto toResponseDto(@Nullable Budget budget) {
        if(budget == null) return null;
        return new BudgetResponseDto(
                budget.getId(),
                budget.getName().getValue(),
                budget.getDescription().getValue(),
                budget.getAmount().getValue(),
                budget.getAmount().getCurrencyCode(),
                budget.getItems(),
                budget.getUserId(),
                budget.getCreatedAt(),
                budget.getUpdatedAt()
        );
    }

    public BudgetResponseDto toResponseDto(@Nullable BudgetProjection budgetProjection) {
        if(budgetProjection == null) return null;
        return new BudgetResponseDto(
                budgetProjection.getId(),
                budgetProjection.getName(),
                budgetProjection.getDescription(),
                budgetProjection.getAmount(),
                budgetProjection.getCurrencyCode(),
                budgetProjection.getItems(),
                budgetProjection.getUserId(),
                budgetProjection.getCreatedAt(),
                budgetProjection.getUpdatedAt()
        );
    }

    public BudgetListResponseDto toResponseDto(@Nullable Page<BudgetProjection> budgetProjectionsList) {
        if(budgetProjectionsList == null) return null;
        return new BudgetListResponseDto(
                budgetProjectionsList.getContent().stream().map(this::toResponseDto).toList(),
                budgetProjectionsList.getTotalElements(),
                budgetProjectionsList.getTotalPages()
        );
    }
}

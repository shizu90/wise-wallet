package dev.gabriel.wisewallet.budget.infrastructure.services;

import dev.gabriel.wisewallet.budget.domain.commands.*;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.infrastructure.projection.BudgetProjectionRepository;
import dev.gabriel.wisewallet.budget.presentation.dtos.BudgetItemRequestDto;
import dev.gabriel.wisewallet.budget.presentation.dtos.BudgetListResponseDto;
import dev.gabriel.wisewallet.budget.presentation.dtos.BudgetRequestDto;
import dev.gabriel.wisewallet.budget.presentation.dtos.BudgetResponseDto;
import dev.gabriel.wisewallet.budget.presentation.dtos.mapper.BudgetDtoMapper;
import dev.gabriel.wisewallet.core.application.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetService {
    private final CommandBus<BudgetCommand> commandBus;
    private final BudgetProjectionRepository budgetProjectionRepository;
    private final BudgetDtoMapper dtoMapper;

    public BudgetResponseDto getBudget(UUID id) {
        return dtoMapper.toResponseDto(budgetProjectionRepository.find(id).orElse(null));
    }

    public BudgetListResponseDto getBudgets(UUID userId, String name, int page, int limit) {
        return dtoMapper.toResponseDto(budgetProjectionRepository.findByUserIdAndName(userId, name, PageRequest.of(page, limit)));
    }

    public BudgetResponseDto newBudget(BudgetRequestDto request) {
        Budget budget = (Budget) commandBus.execute(new CreateBudgetCommand(
                UUID.randomUUID(),
                request.name(),
                request.description(),
                request.currencyCode(),
                request.userId()
        ));

        return dtoMapper.toResponseDto(budget);
    }

    public BudgetResponseDto updateBudget(BudgetRequestDto request) {
        Budget budget = null;
        if(!(request.name() == null || request.name().isBlank())) {
            budget = (Budget) commandBus.execute(new RenameBudgetCommand(
                    request.id(),
                    request.name()
            ));
        }
        if(!(request.description() == null || request.description().isBlank())) {
            budget = (Budget) commandBus.execute(new ChangeBudgetDescriptionCommand(
                    request.id(),
                    request.description()
            ));
        }
        if(!(request.currencyCode() == null || request.currencyCode().isBlank())) {
            budget = (Budget) commandBus.execute(new UpdateBudgetAmountCommand(
                    request.id(),
                    null,
                    request.currencyCode()
            ));
        }

        return dtoMapper.toResponseDto(budget);
    }

    public BudgetResponseDto addItem(UUID budgetId, BudgetItemRequestDto request) {
        Budget budget = (Budget) commandBus.execute(new AddBudgetItemCommand(
                budgetId,
                request.billId(),
                request.name(),
                request.amount(),
                request.currencyCode(),
                request.type()
        ));

        return dtoMapper.toResponseDto(budget);
    }

    public BudgetResponseDto removeItem(UUID budgetId, UUID billId) {
        Budget budget = (Budget) commandBus.execute(new RemoveBudgetItemCommand(
                budgetId,
                billId
        ));

        return dtoMapper.toResponseDto(budget);
    }

    public void deleteBudget(UUID id) {
        commandBus.execute(new DeleteBudgetCommand(id));
    }
}

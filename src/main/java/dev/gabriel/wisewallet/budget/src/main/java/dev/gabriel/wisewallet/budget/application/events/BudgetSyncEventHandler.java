package dev.gabriel.wisewallet.budget.application.events;

import dev.gabriel.wisewallet.budget.domain.models.AggregateType;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.infrastructure.projection.BudgetProjection;
import dev.gabriel.wisewallet.budget.infrastructure.projection.BudgetProjectionRepository;
import dev.gabriel.wisewallet.core.application.SyncEventHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetSyncEventHandler implements SyncEventHandler {
    private final BudgetProjectionRepository projectionRepository;

    @Override
    public void handleEvents(Aggregate aggregate) {
        Budget budget = (Budget) aggregate;

        BudgetProjection budgetProjection = BudgetProjection.create(
                budget.getId(),
                budget.getName().getValue(),
                budget.getDescription().getValue(),
                budget.getAmount().getValue(),
                budget.getAmount().getCurrencyCode(),
                budget.getItems(),
                budget.getUserId(),
                budget.getCreatedAt(),
                budget.getUpdatedAt(),
                budget.getIsDeleted()
        );

        projectionRepository.save(budgetProjection);
    }

    @Override
    @NonNull
    public String getAggregateType() {
        return AggregateType.BUDGET.toString();
    }
}

package dev.gabriel.wisewallet.budget.infrastructure.eventstore;

import dev.gabriel.wisewallet.budget.domain.models.AggregateType;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.repositories.BudgetRepository;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.services.AggregateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetEventStore implements BudgetRepository {
    private final AggregateService aggregateService;

    @Override
    public Optional<Budget> load(UUID id) {
        Budget budget = (Budget) aggregateService.load(AggregateType.BUDGET.toString(), id, null);
        return Optional.ofNullable(budget);
    }

    @Override
    public void saveChanges(Budget budget) {
        aggregateService.save(budget);
    }
}
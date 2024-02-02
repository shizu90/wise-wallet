package dev.gabriel.wisewallet.budget.domain.repositories;

import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;

import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository extends DomainRepository {
    Optional<Budget> load(UUID id);
    void saveChanges(Budget budget);
}

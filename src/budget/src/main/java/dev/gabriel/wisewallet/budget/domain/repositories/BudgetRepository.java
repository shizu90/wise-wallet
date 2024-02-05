package dev.gabriel.wisewallet.budget.domain.repositories;

import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import jakarta.annotation.Nullable;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository extends DomainRepository {
    Optional<Budget> load(@NonNull UUID id, @Nullable Long version);

    void saveChanges(@NonNull Budget budget);
}

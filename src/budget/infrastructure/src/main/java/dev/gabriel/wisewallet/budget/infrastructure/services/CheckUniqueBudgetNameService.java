package dev.gabriel.wisewallet.budget.infrastructure.services;

import dev.gabriel.wisewallet.budget.domain.services.CheckUniqueBudgetName;
import dev.gabriel.wisewallet.budget.infrastructure.projection.BudgetProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckUniqueBudgetNameService implements CheckUniqueBudgetName {
    private final BudgetProjectionRepository budgetProjectionRepository;

    @Override
    public boolean exists(String name, UUID userId) {
        return !budgetProjectionRepository.findByNameAndUserId(name, userId).isEmpty();
    }
}

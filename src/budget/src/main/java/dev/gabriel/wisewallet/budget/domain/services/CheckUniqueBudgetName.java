package dev.gabriel.wisewallet.budget.domain.services;

import java.util.UUID;

public interface CheckUniqueBudgetName {
    boolean exists(String name, UUID userId);
}

package dev.gabriel.wisewallet.budget.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BudgetAlreadyDeletedException extends DomainException {
    public BudgetAlreadyDeletedException(String msg) {
        super(msg);
    }
}

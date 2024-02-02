package dev.gabriel.wisewallet.budget.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BudgetNotFoundException extends DomainException {
    public BudgetNotFoundException(String msg) {
        super(msg);
    }
}

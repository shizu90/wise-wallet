package dev.gabriel.wisewallet.budget.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BudgetAlreadyExistsException extends DomainException {
    public BudgetAlreadyExistsException(String msg) {
        super(msg);
    }
}

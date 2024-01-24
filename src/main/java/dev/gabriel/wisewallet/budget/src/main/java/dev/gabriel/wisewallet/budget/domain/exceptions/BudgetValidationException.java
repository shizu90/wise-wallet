package dev.gabriel.wisewallet.budget.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BudgetValidationException extends DomainException {
    public BudgetValidationException(String msg) {
        super(msg);
    }
}

package dev.gabriel.wisewallet.budget.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BudgetItemAlreadyPresentException extends DomainException {
    public BudgetItemAlreadyPresentException(String msg) {
        super(msg);
    }
}

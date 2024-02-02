package dev.gabriel.wisewallet.budget.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BudgetItemNotPresentException extends DomainException {
    public BudgetItemNotPresentException(String msg) {
        super(msg);
    }
}

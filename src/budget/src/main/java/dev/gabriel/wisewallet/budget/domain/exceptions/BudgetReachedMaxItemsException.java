package dev.gabriel.wisewallet.budget.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BudgetReachedMaxItemsException extends DomainException {
    public BudgetReachedMaxItemsException(String msg) {
        super(msg);
    }
}

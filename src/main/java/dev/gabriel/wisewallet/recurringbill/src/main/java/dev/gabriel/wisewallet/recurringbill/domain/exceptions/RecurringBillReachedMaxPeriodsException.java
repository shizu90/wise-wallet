package dev.gabriel.wisewallet.recurringbill.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class RecurringBillReachedMaxPeriodsException extends DomainException {
    public RecurringBillReachedMaxPeriodsException(String msg) {
        super(msg);
    }
}

package dev.gabriel.wisewallet.recurringbill.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class RecurringBillAlreadyDeletedException extends DomainException {
    public RecurringBillAlreadyDeletedException(String msg) {
        super(msg);
    }
}

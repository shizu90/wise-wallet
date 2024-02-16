package dev.gabriel.wisewallet.recurringbill.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class RecurringBillAlreadyExistsException extends DomainException {
    public RecurringBillAlreadyExistsException(String msg) {
        super(msg);
    }
}

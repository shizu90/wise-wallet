package dev.gabriel.wisewallet.recurringbill.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class RecurringBillNotFoundException extends DomainException {
    public RecurringBillNotFoundException(String msg) {
        super(msg);
    }
}

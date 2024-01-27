package dev.gabriel.wisewallet.recurringbill.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class RecurringBillValidationException extends DomainException {
    public RecurringBillValidationException(String msg) {
        super(msg);
    }
}

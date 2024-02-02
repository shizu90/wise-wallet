package dev.gabriel.wisewallet.bill.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BillValidationException extends DomainException {
    public BillValidationException(String msg) {
        super(msg);
    }
}

package dev.gabriel.wisewallet.bill.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BillNotFoundException extends DomainException {
    public BillNotFoundException(String msg) {
        super(msg);
    }
}

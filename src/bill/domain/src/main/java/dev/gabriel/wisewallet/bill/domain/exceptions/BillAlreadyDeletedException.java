package dev.gabriel.wisewallet.bill.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class BillAlreadyDeletedException extends DomainException {
    public BillAlreadyDeletedException(String msg) {
        super(msg);
    }
}

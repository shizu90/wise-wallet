package dev.gabriel.wisewallet.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class WalletValidationException extends DomainException {
    public WalletValidationException(String msg) {
        super(msg);
    }
}

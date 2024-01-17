package dev.gabriel.wisewallet.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class WalletNotFoundException extends DomainException {
    public WalletNotFoundException(String msg) {
        super(msg);
    }
}

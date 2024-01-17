package dev.gabriel.wisewallet.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class WalletAlreadyExistsException extends DomainException {
    public WalletAlreadyExistsException(String msg) {
        super(msg);
    }
}

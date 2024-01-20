package dev.gabriel.wisewallet.wallet.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class WalletAlreadyDeletedException extends DomainException {
    public WalletAlreadyDeletedException(String msg) {
        super(msg);
    }
}

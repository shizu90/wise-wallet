package dev.gabriel.wisewallet.wallet.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class ReachedMaxWalletsException extends DomainException {
    public ReachedMaxWalletsException(String msg) {
        super(msg);
    }
}

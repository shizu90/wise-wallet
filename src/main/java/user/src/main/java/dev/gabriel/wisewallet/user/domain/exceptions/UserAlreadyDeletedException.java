package dev.gabriel.wisewallet.user.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class UserAlreadyDeletedException extends DomainException {
    public UserAlreadyDeletedException(String msg) {
        super(msg);
    }
}

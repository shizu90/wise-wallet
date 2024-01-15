package dev.gabriel.wisewallet.user.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class UserAlreadyExistsException extends DomainException {
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}

package dev.gabriel.wisewallet.user.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}

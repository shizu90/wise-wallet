package dev.gabriel.wisewallet.user.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class UserValidationException extends DomainException {
    public UserValidationException(String msg) {
        super(msg);
    }
}

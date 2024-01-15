package dev.gabriel.wisewallet.core.domain.exceptions;

public abstract class DomainException extends RuntimeException {
    protected DomainException(String msg) {
        super(msg);
    }
}

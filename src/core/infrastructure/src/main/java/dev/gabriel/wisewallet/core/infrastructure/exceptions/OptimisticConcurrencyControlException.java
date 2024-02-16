package dev.gabriel.wisewallet.core.infrastructure.exceptions;

public class OptimisticConcurrencyControlException extends RuntimeException {
    public OptimisticConcurrencyControlException(String msg) {
        super(msg);
    }
}

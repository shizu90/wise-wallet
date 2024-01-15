package dev.gabriel.wisewallet.core.infrastructure.exceptions;

public class SnapshotNotFoundException extends RuntimeException {
    public SnapshotNotFoundException(String msg) {
        super(msg);
    }
}

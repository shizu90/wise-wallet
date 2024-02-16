package dev.gabriel.wisewallet.auth.services;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException(String msg) {
        super(msg);
    }
}

package dev.gabriel.wisewallet.user.domain.services;

public interface EncryptPassword {
    String encrypt(String password);
    boolean validate(String hashPassword, String password);
}

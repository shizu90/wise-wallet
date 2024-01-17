package dev.gabriel.wisewallet.domain.services;

public interface CheckUniqueWalletNameService {
    boolean exists(String name);
}

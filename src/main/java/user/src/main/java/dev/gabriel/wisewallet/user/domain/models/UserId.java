package dev.gabriel.wisewallet.user.domain.models;

import dev.gabriel.wisewallet.core.domain.models.Identity;

import java.util.UUID;

public class UserId extends Identity {
    private UserId(UUID value) {
        super(value);
    }

    public static UserId create(UUID value) {
        return new UserId(value);
    }
}

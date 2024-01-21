package dev.gabriel.wisewallet.reminder.domain.models;

import dev.gabriel.wisewallet.core.domain.models.Identity;

import java.util.UUID;

public class ReminderId extends Identity {
    private ReminderId(UUID value) {
        super(value);
    }

    public static ReminderId create(UUID value) {
        return new ReminderId(value);
    }
}

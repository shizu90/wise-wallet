package dev.gabriel.wisewallet.category.domain.models;

import dev.gabriel.wisewallet.core.domain.models.Identity;

import java.util.UUID;

public class CategoryId extends Identity {
    private CategoryId(UUID value) {
        super(value);
    }

    public static CategoryId create(UUID value) {
        return new CategoryId(value);
    }
}

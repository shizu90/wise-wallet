package dev.gabriel.wisewallet.category.domain.services;

import java.util.UUID;

public interface CheckUniqueCategoryName {
    boolean exists(String name, UUID userId);
}

package dev.gabriel.wisewallet.category.domain.repositories;

import dev.gabriel.wisewallet.category.domain.models.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Optional<Category> load(UUID id);

    void saveChanges(Category category);
}

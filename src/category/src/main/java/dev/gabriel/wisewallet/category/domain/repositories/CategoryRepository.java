package dev.gabriel.wisewallet.category.domain.repositories;

import dev.gabriel.wisewallet.category.domain.models.Category;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Optional<Category> load(@NonNull UUID id);

    Optional<Category> load(@NonNull UUID id, Long version);

    void saveChanges(@NonNull Category category);
}

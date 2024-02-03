package dev.gabriel.wisewallet.category.infrastructure.eventstore;

import dev.gabriel.wisewallet.category.domain.models.AggregateType;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.domain.repositories.CategoryRepository;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.services.AggregateService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryEventStore implements CategoryRepository {
    private final AggregateService aggregateService;

    @Override
    public Optional<Category> load(@NonNull UUID id) {
        Category category = (Category) aggregateService.load(AggregateType.CATEGORY.toString(), id, null);
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<Category> load(@NonNull UUID id, Long version) {
        Category category = (Category) aggregateService.load(AggregateType.CATEGORY.toString(), id, version);
        return Optional.ofNullable(category);
    }

    @Override
    public void saveChanges(@NonNull Category category) {
        aggregateService.save(category);
    }
}

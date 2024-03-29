package dev.gabriel.wisewallet.category.infrastructure.services;

import dev.gabriel.wisewallet.category.application.events.CategorySyncEventHandler;
import dev.gabriel.wisewallet.category.domain.models.AggregateType;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.infrastructure.projection.CategoryProjection;
import dev.gabriel.wisewallet.category.infrastructure.projection.CategoryProjectionRepository;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategorySyncEventHandlerImpl implements CategorySyncEventHandler {
    private final CategoryProjectionRepository projectionRepository;

    @Override
    public void handleEvents(Aggregate aggregate) {
        Category category = (Category) aggregate;
        CategoryProjection categoryProjection = CategoryProjection.create(
                category.getId(),
                category.getName().getValue(),
                category.getUserId(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getIsDeleted()
        );

        projectionRepository.save(categoryProjection);
    }

    @Override
    @NonNull
    public String getAggregateType() {
        return AggregateType.CATEGORY.toString();
    }
}

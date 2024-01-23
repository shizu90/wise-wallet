package dev.gabriel.wisewallet.category.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.category.domain.events.CategoryCreatedEvent;
import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.category.domain.events.CategoryRenamedEvent;
import dev.gabriel.wisewallet.category.domain.exceptions.CategoryAlreadyDeletedException;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Category extends Aggregate {
    private CategoryName name;
    private UUID userId;
    private Boolean isDeleted;

    @JsonCreator
    public Category(UUID id, Long version) {
        super(id, version);
    }

    private Category(UUID id, String name, UUID userId) {
        this(id, 0L);

        CategoryName.validate(name);
        applyChange(new CategoryCreatedEvent(
                id,
                getNextVersion(),
                name,
                userId
        ));
    }

    public static Category create(UUID id, String name, UUID userId) {
        return new Category(id, name, userId);
    }

    public void rename(String name) {
        CategoryName.validate(name);

        applyChange(new CategoryRenamedEvent(
                id,
                getNextVersion(),
                name
        ));
    }

    public void delete() {
        if(isDeleted)
            throw new CategoryAlreadyDeletedException("Category %s already deleted.".formatted(id));

        applyChange(new CategoryDeletedEvent(
                id,
                getNextVersion()
        ));
    }

    @SuppressWarnings("unused")
    private void apply(CategoryCreatedEvent event) {
        this.id = event.getAggregateId();
        this.name = CategoryName.create(event.getName());
        this.userId = event.getUserId();
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(CategoryRenamedEvent event) {
        this.name = CategoryName.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(CategoryDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public String getAggregateType() {
        return AggregateType.CATEGORY.toString();
    }
}

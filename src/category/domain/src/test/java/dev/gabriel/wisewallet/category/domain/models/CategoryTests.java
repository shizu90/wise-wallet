package dev.gabriel.wisewallet.category.domain.models;

import dev.gabriel.wisewallet.category.domain.events.CategoryCreatedEvent;
import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.category.domain.events.CategoryRenamedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CategoryTests {
    @Test
    @DisplayName("Should create category successfully.")
    void createCategory() {
        Category category = Category.create(
                UUID.randomUUID(),
                "Name",
                UUID.randomUUID()
        );

        Assertions.assertEquals(1L, category.getVersion());
        Assertions.assertInstanceOf(CategoryCreatedEvent.class, category.getChanges().get(0));
    }

    @Test
    @DisplayName("Should rename category successfully.")
    void renameCategory() {
        Category category = Category.create(
                UUID.randomUUID(),
                "Name",
                UUID.randomUUID()
        );

        category.rename("NewName");

        Assertions.assertEquals(2L, category.getVersion());
        Assertions.assertInstanceOf(CategoryRenamedEvent.class, category.getChanges().get(1));
        Assertions.assertEquals("NewName", category.getName().getValue());
    }

    @Test
    @DisplayName("Should delete category successfully.")
    void deleteCategory() {
        Category category = Category.create(
                UUID.randomUUID(),
                "Name",
                UUID.randomUUID()
        );
        category.delete();

        Assertions.assertEquals(2L, category.getVersion());
        Assertions.assertInstanceOf(CategoryDeletedEvent.class, category.getChanges().get(1));
        Assertions.assertTrue(category.getIsDeleted());
    }
}

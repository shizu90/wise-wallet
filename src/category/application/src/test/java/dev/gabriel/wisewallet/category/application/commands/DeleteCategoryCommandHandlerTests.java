package dev.gabriel.wisewallet.category.application.commands;

import dev.gabriel.wisewallet.category.domain.commands.DeleteCategoryCommand;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.domain.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class DeleteCategoryCommandHandlerTests {
    @Mock
    private CategoryRepository categoryRepository;
    @Autowired
    @InjectMocks
    private DeleteCategoryCommandHandler deleteCategoryCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    private Category populate() {
        return Category.create(
                UUID.randomUUID(),
                "Name",
                UUID.randomUUID()
        );
    }

    @Test
    @DisplayName("Delete category command handler test")
    void deleteCategory() {
        Category category = populate();
        DeleteCategoryCommand command = new DeleteCategoryCommand(category.getId());

        Mockito.when(categoryRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(category));

        Category returnedCategory = deleteCategoryCommandHandler.handle(command);

        Assertions.assertTrue(returnedCategory.getIsDeleted());
    }
}

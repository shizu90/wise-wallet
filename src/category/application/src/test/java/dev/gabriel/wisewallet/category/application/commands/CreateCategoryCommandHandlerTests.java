package dev.gabriel.wisewallet.category.application.commands;

import dev.gabriel.wisewallet.category.domain.commands.CreateCategoryCommand;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.domain.repositories.CategoryRepository;
import dev.gabriel.wisewallet.category.domain.services.CheckUniqueCategoryName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CreateCategoryCommandHandlerTests {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CheckUniqueCategoryName checkUniqueCategoryName;
    @Autowired
    @InjectMocks
    private CreateCategoryCommandHandler createCategoryCommandHandler;

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
    @DisplayName("Create category command handler test")
    void createCategory() {
        Category category = populate();
        CreateCategoryCommand command = new CreateCategoryCommand(category.getId(), category.getName().getValue(), category.getUserId());

        Mockito.when(checkUniqueCategoryName.exists(command.getName(), command.getUserId())).thenReturn(false);

        Category returnedCategory = createCategoryCommandHandler.handle(command);

        Assertions.assertEquals(command.getAggregateId(), returnedCategory.getId());
    }
}

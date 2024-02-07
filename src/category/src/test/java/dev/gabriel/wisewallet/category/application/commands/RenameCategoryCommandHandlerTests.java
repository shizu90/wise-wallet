package dev.gabriel.wisewallet.category.application.commands;

import dev.gabriel.wisewallet.category.domain.commands.RenameCategoryCommand;
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

import java.util.Optional;
import java.util.UUID;

public class RenameCategoryCommandHandlerTests {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CheckUniqueCategoryName checkUniqueCategoryName;
    @Autowired
    @InjectMocks
    private RenameCategoryCommandHandler renameCategoryCommandHandler;

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
    @DisplayName("Rename category command handler test")
    void renameCategory() {
        Category category = populate();
        RenameCategoryCommand command = new RenameCategoryCommand(category.getId(), "NewName");

        Mockito.when(checkUniqueCategoryName.exists(command.getName(), category.getUserId())).thenReturn(false);
        Mockito.when(categoryRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(category));

        Category returnedCategory = renameCategoryCommandHandler.handle(command);

        Assertions.assertEquals(command.getName(), returnedCategory.getName().getValue());
    }
}

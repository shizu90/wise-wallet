package dev.gabriel.wisewallet.category.application.commands;

import dev.gabriel.wisewallet.category.domain.commands.CreateCategoryCommand;
import dev.gabriel.wisewallet.category.domain.exceptions.CategoryAlreadyExistsException;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.domain.repositories.CategoryRepository;
import dev.gabriel.wisewallet.category.domain.services.CheckUniqueCategoryName;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand> {
    private final CategoryRepository categoryRepository;
    private final CheckUniqueCategoryName checkUniqueCategoryName;

    @Override
    public Category handle(@NonNull CreateCategoryCommand command) {
        if(checkUniqueCategoryName.exists(command.getName(), command.getUserId()))
            throw new CategoryAlreadyExistsException("Category with name %s already exists.".formatted(command.getName()));

        Category category = Category.create(UUID.randomUUID(), command.getName(), command.getUserId());

        categoryRepository.saveChanges(category);

        return category;
    }

    @Override
    @NonNull
    public Class<CreateCategoryCommand> getCommandType() {
        return CreateCategoryCommand.class;
    }
}

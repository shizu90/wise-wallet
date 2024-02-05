package dev.gabriel.wisewallet.category.application.commands;

import dev.gabriel.wisewallet.category.domain.commands.RenameCategoryCommand;
import dev.gabriel.wisewallet.category.domain.exceptions.CategoryAlreadyExistsException;
import dev.gabriel.wisewallet.category.domain.exceptions.CategoryNotFoundException;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.domain.repositories.CategoryRepository;
import dev.gabriel.wisewallet.category.domain.services.CheckUniqueCategoryName;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RenameCategoryCommandHandler implements CommandHandler<RenameCategoryCommand> {
    private final CategoryRepository categoryRepository;
    private final CheckUniqueCategoryName checkUniqueCategoryName;

    @Override
    public Category handle(@NonNull RenameCategoryCommand command) {
        Category category = categoryRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new CategoryNotFoundException("Category %s was not found.".formatted(command.getAggregateId())));

        if(command.getName().equals(category.getName().getValue())) return category;

        if(checkUniqueCategoryName.exists(command.getName(), category.getUserId()))
            throw new CategoryAlreadyExistsException("Category with name %s already exists.".formatted(command.getName()));

        category.rename(command.getName());

        categoryRepository.saveChanges(category);

        return category;
    }

    @Override
    @NonNull
    public Class<RenameCategoryCommand> getCommandType() {
        return RenameCategoryCommand.class;
    }
}

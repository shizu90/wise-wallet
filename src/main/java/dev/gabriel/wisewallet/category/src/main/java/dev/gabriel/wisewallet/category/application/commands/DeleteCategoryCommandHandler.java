package dev.gabriel.wisewallet.category.application.commands;

import dev.gabriel.wisewallet.category.domain.commands.DeleteCategoryCommand;
import dev.gabriel.wisewallet.category.domain.exceptions.CategoryNotFoundException;
import dev.gabriel.wisewallet.category.domain.models.Category;
import dev.gabriel.wisewallet.category.domain.repositories.CategoryRepository;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteCategoryCommandHandler implements CommandHandler<DeleteCategoryCommand> {
    private final CategoryRepository categoryRepository;

    @Override
    public Category handle(@NonNull DeleteCategoryCommand command) {
        Category category = categoryRepository.load(command.getAggregateId()).orElseThrow(() ->
                new CategoryNotFoundException("Category %s was not found.".formatted(command.getAggregateId())));

        category.delete();

        categoryRepository.saveChanges(category);

        return category;
    }

    @Override
    @NonNull
    public Class<DeleteCategoryCommand> getCommandType() {
        return DeleteCategoryCommand.class;
    }
}

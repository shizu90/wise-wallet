package dev.gabriel.wisewallet.category.infrastructure.services;

import dev.gabriel.wisewallet.category.application.events.CategoryAsyncEventHandler;
import dev.gabriel.wisewallet.category.domain.commands.CategoryCommand;
import dev.gabriel.wisewallet.category.domain.commands.DeleteCategoryCommand;
import dev.gabriel.wisewallet.category.infrastructure.projection.CategoryProjection;
import dev.gabriel.wisewallet.category.infrastructure.projection.CategoryProjectionRepository;
import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryAsyncEventHandlerImpl implements CategoryAsyncEventHandler {
    private final CategoryProjectionRepository categoryProjectionRepository;
    private final CommandBus<CategoryCommand> commandBus;

    @Override
    public void handle(UserDeletedEvent event) {
        List<CategoryProjection> categories = categoryProjectionRepository.findByUserId(event.getAggregateId());

        for(CategoryProjection category : categories) {
            commandBus.execute(new DeleteCategoryCommand(category.getId()));
        }
    }
}

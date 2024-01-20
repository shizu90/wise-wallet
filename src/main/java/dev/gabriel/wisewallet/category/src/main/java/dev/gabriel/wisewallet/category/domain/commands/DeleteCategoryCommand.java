package dev.gabriel.wisewallet.category.domain.commands;

import java.util.UUID;

public class DeleteCategoryCommand extends CategoryCommand {
    public DeleteCategoryCommand(UUID id) {
        super(id);
    }
}

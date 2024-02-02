package dev.gabriel.wisewallet.category.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCategoryCommand extends CategoryCommand {
    private final String name;
    private final UUID userId;

    public CreateCategoryCommand(UUID id, String name, UUID userId) {
        super(id);
        this.name = name;
        this.userId = userId;
    }
}

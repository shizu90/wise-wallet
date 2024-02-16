package dev.gabriel.wisewallet.category.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RenameCategoryCommand extends CategoryCommand {
    private final String name;

    public RenameCategoryCommand(UUID id, String name) {
        super(id);
        this.name = name;
    }
}

package dev.gabriel.wisewallet.category.domain.commands;

import dev.gabriel.wisewallet.category.domain.models.AggregateType;
import dev.gabriel.wisewallet.core.domain.commands.Command;

import java.util.UUID;

public abstract class CategoryCommand extends Command {
    protected CategoryCommand(UUID id) {
        super(id, AggregateType.CATEGORY.toString());
    }
}

package dev.gabriel.wisewallet.budget.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeBudgetDescriptionCommand extends BudgetCommand {
    private final String description;

    public ChangeBudgetDescriptionCommand(UUID id, String description) {
        super(id);
        this.description = description;
    }
}

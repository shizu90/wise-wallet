package dev.gabriel.wisewallet.budget.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RenameBudgetCommand extends BudgetCommand {
    private final String name;

    public RenameBudgetCommand(UUID id, String name) {
        super(id);
        this.name = name;
    }
}

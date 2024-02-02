package dev.gabriel.wisewallet.budget.domain.commands;

import java.util.UUID;

public class DeleteBudgetCommand extends BudgetCommand {
    public DeleteBudgetCommand(UUID id) {
        super(id);
    }
}

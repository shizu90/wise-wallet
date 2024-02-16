package dev.gabriel.wisewallet.budget.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RemoveBudgetItemCommand extends BudgetCommand {
    private final UUID billId;

    public RemoveBudgetItemCommand(UUID id, UUID billId) {
        super(id);
        this.billId = billId;
    }
}

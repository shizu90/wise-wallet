package dev.gabriel.wisewallet.budget.domain.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class AddBudgetItemCommand extends BudgetCommand {
    private final UUID billId;
    private final BigDecimal amount;

    public AddBudgetItemCommand(UUID id, UUID billId, BigDecimal amount) {
        super(id);
        this.billId = billId;
        this.amount = amount;
    }
}

package dev.gabriel.wisewallet.budget.domain.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class AddBudgetItemCommand extends BudgetCommand {
    private final UUID billId;
    private final String name;
    private final BigDecimal amount;
    private final String currencyCode;
    private final String type;

    public AddBudgetItemCommand(UUID id, UUID billId, String name, BigDecimal amount, String currencyCode, String type) {
        super(id);
        this.billId = billId;
        this.name = name;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.type = type;
    }
}

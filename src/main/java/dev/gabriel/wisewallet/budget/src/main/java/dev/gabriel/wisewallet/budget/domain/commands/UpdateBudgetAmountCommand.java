package dev.gabriel.wisewallet.budget.domain.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class UpdateBudgetAmountCommand extends BudgetCommand {
    private final BigDecimal amount;
    private final String currencyCode;

    public UpdateBudgetAmountCommand(UUID id, BigDecimal amount, String currencyCode) {
        super(id);
        this.amount = amount;
        this.currencyCode = currencyCode;
    }
}

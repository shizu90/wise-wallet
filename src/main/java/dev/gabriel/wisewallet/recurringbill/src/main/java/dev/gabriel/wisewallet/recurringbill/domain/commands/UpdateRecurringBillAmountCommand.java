package dev.gabriel.wisewallet.recurringbill.domain.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class UpdateRecurringBillAmountCommand extends RecurringBillCommand {
    private final BigDecimal amount;
    private final String currencyCode;

    public UpdateRecurringBillAmountCommand(UUID id, BigDecimal amount, String currencyCode) {
        super(id);
        this.amount = amount;
        this.currencyCode = currencyCode;
    }
}

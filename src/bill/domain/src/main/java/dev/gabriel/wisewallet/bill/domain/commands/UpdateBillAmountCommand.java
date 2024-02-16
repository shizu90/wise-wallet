package dev.gabriel.wisewallet.bill.domain.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class UpdateBillAmountCommand extends BillCommand {
    private final BigDecimal amount;
    private final String currencyCode;

    public UpdateBillAmountCommand(UUID id, BigDecimal amount, String currencyCode) {
        super(id);
        this.amount = amount;
        this.currencyCode = currencyCode;
    }
}

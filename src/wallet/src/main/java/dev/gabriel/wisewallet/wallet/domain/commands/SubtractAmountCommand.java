package dev.gabriel.wisewallet.wallet.domain.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class SubtractAmountCommand extends WalletCommand {
    private final BigDecimal amount;
    private final String currencyCode;

    public SubtractAmountCommand(UUID id, BigDecimal amount, String currencyCode) {
        super(id);
        this.amount = amount;
        this.currencyCode = currencyCode;
    }
}

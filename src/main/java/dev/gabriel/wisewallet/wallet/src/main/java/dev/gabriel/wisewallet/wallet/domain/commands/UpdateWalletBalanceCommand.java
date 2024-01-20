package dev.gabriel.wisewallet.wallet.domain.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class UpdateWalletBalanceCommand extends WalletCommand {
    private final BigDecimal balance;
    private final String currencyCode;

    public UpdateWalletBalanceCommand(UUID id, BigDecimal balance, String currencyCode) {
        super(id);
        this.balance = balance;
        this.currencyCode = currencyCode;
    }
}

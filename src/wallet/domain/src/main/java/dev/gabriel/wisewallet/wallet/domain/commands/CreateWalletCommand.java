package dev.gabriel.wisewallet.wallet.domain.commands;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CreateWalletCommand extends WalletCommand {
    private final String name;
    private final String description;
    private final BigDecimal balance;
    private final String currencyCode;
    private final WalletType type;
    private final UUID userId;

    public CreateWalletCommand(UUID id,
                               String name,
                               String description,
                               BigDecimal balance,
                               String currencyCode,
                               WalletType type,
                               UUID userId
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.balance = balance;
        this.currencyCode = currencyCode;
        this.type = type;
        this.userId = userId;
    }
}

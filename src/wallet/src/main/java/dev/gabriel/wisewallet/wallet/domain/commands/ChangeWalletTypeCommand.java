package dev.gabriel.wisewallet.wallet.domain.commands;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeWalletTypeCommand extends WalletCommand {
    private final WalletType type;

    public ChangeWalletTypeCommand(UUID id, WalletType type) {
        super(id);
        this.type = type;
    }
}

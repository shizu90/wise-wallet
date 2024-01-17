package dev.gabriel.wisewallet.domain.commands;

import dev.gabriel.wisewallet.domain.models.WalletType;
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

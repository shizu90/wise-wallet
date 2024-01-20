package dev.gabriel.wisewallet.wallet.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeWalletDescriptionCommand extends WalletCommand {
    private final String description;

    public ChangeWalletDescriptionCommand(UUID id, String description) {
        super(id);
        this.description = description;
    }
}

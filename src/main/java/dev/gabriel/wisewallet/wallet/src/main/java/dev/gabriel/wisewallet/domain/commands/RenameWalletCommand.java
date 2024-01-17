package dev.gabriel.wisewallet.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RenameWalletCommand extends WalletCommand {
    private final String name;

    public RenameWalletCommand(UUID id, String name) {
        super(id);
        this.name = name;
    }
}

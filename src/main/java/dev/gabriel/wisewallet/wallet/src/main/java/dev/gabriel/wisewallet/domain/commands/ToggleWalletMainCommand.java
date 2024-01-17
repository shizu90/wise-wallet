package dev.gabriel.wisewallet.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ToggleWalletMainCommand extends WalletCommand {
    private final Boolean main;

    public ToggleWalletMainCommand(UUID id, Boolean main) {
        super(id);
        this.main = main;
    }
}

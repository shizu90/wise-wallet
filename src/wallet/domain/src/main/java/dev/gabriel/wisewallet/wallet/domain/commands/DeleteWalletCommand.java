package dev.gabriel.wisewallet.wallet.domain.commands;

import java.util.UUID;

public class DeleteWalletCommand extends WalletCommand {
    public DeleteWalletCommand(UUID id) {
        super(id);
    }
}

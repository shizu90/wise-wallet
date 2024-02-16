package dev.gabriel.wisewallet.wallet.domain.commands;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.wallet.domain.models.AggregateType;

import java.util.UUID;

public abstract class WalletCommand extends Command {
    public WalletCommand(UUID id) {
        super(id, AggregateType.WALLET.toString());
    }
}

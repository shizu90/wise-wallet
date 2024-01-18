package dev.gabriel.wisewallet.bill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeBillWalletCommand extends BillCommand {
    private final UUID walletId;

    public ChangeBillWalletCommand(UUID id, UUID walletId) {
        super(id);
        this.walletId = walletId;
    }
}

package dev.gabriel.wisewallet.recurringbill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeRecurringBillWalletCommand extends RecurringBillCommand {
    private final UUID walletId;

    public ChangeRecurringBillWalletCommand(UUID id, UUID walletId) {
        super(id);
        this.walletId = walletId;
    }
}

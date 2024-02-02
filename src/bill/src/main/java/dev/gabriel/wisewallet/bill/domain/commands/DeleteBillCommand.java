package dev.gabriel.wisewallet.bill.domain.commands;

import java.util.UUID;

public class DeleteBillCommand extends BillCommand {
    public DeleteBillCommand(UUID id) {
        super(id);
    }
}

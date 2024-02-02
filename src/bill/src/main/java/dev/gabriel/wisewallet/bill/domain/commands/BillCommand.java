package dev.gabriel.wisewallet.bill.domain.commands;

import dev.gabriel.wisewallet.bill.domain.models.AggregateType;
import dev.gabriel.wisewallet.core.domain.commands.Command;

import java.util.UUID;

public abstract class BillCommand extends Command {
    protected BillCommand(UUID billId) {
        super(billId, AggregateType.BILL.toString());
    }
}

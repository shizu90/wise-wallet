package dev.gabriel.wisewallet.recurringbill.domain.commands;

import dev.gabriel.wisewallet.core.domain.commands.Command;
import dev.gabriel.wisewallet.recurringbill.domain.models.AggregateType;

import java.util.UUID;

public abstract class RecurringBillCommand extends Command {
    protected RecurringBillCommand(UUID id) {
        super(id, AggregateType.RECURRING_BILL.toString());
    }
}

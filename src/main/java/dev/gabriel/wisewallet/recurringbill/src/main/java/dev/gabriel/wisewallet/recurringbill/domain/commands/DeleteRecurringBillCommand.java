package dev.gabriel.wisewallet.recurringbill.domain.commands;

import java.util.UUID;

public class DeleteRecurringBillCommand extends RecurringBillCommand {
    public DeleteRecurringBillCommand(UUID id) {
        super(id);
    }
}

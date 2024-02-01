package dev.gabriel.wisewallet.recurringbill.domain.commands;

import java.util.UUID;

public class ResetRecurringBillCommand extends RecurringBillCommand {
    public ResetRecurringBillCommand(UUID id) {
        super(id);
    }
}

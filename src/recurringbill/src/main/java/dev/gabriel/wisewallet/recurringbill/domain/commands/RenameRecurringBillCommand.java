package dev.gabriel.wisewallet.recurringbill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RenameRecurringBillCommand extends RecurringBillCommand {
    private final String name;

    public RenameRecurringBillCommand(UUID id, String name) {
        super(id);
        this.name = name;
    }
}

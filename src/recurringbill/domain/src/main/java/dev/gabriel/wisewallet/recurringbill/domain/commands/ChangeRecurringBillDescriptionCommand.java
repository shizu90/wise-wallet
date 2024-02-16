package dev.gabriel.wisewallet.recurringbill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeRecurringBillDescriptionCommand extends RecurringBillCommand {
    private final String description;

    public ChangeRecurringBillDescriptionCommand(UUID id, String description) {
        super(id);
        this.description = description;
    }
}

package dev.gabriel.wisewallet.recurringbill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeRecurringBillCategoryCommand extends RecurringBillCommand {
    private final UUID categoryId;

    public ChangeRecurringBillCategoryCommand(UUID id, UUID categoryId) {
        super(id);
        this.categoryId = categoryId;
    }
}

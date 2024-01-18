package dev.gabriel.wisewallet.bill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeBillCategoryCommand extends BillCommand {
    private final UUID categoryId;

    public ChangeBillCategoryCommand(UUID id, UUID categoryId) {
        super(id);
        this.categoryId = categoryId;
    }
}

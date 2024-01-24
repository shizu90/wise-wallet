package dev.gabriel.wisewallet.budget.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateBudgetCommand extends BudgetCommand {
    private final String name;
    private final String description;
    private final String currencyCode;
    private final UUID userId;

    public CreateBudgetCommand(UUID id, String name, String description, String currencyCode, UUID userId) {
        super(id);
        this.name = name;
        this.description = description;
        this.currencyCode = currencyCode;
        this.userId = userId;
    }
}

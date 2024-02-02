package dev.gabriel.wisewallet.budget.domain.commands;

import dev.gabriel.wisewallet.budget.domain.models.AggregateType;
import dev.gabriel.wisewallet.core.domain.commands.Command;

import java.util.UUID;

public abstract class BudgetCommand extends Command {
    protected BudgetCommand(UUID aggregateId) {
        super(aggregateId, AggregateType.BUDGET.toString());
    }
}

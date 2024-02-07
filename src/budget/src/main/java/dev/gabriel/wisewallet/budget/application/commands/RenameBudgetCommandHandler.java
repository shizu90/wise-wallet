package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.RenameBudgetCommand;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetAlreadyExistsException;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetNotFoundException;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.repositories.BudgetRepository;
import dev.gabriel.wisewallet.budget.domain.services.CheckUniqueBudgetName;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RenameBudgetCommandHandler implements CommandHandler<RenameBudgetCommand> {
    private final BudgetRepository budgetRepository;
    private final CheckUniqueBudgetName checkUniqueBudgetName;

    @Override
    public Budget handle(@NonNull RenameBudgetCommand command) {
        Budget budget = budgetRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new BudgetNotFoundException("Budget %s was not found.".formatted(command.getAggregateId())));

        if(checkUniqueBudgetName.exists(command.getName(), budget.getUserId()))
            throw new BudgetAlreadyExistsException("Budget with name %s already exists.".formatted(command.getName()));

        if(command.getName().equals(budget.getName().getValue())) return budget;

        budget.rename(command.getName());

        budgetRepository.saveChanges(budget);

        return budget;
    }

    @Override
    @NonNull
    public Class<RenameBudgetCommand> getCommandType() {
        return RenameBudgetCommand.class;
    }
}

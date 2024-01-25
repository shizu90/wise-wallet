package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.DeleteBudgetCommand;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetNotFoundException;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.repositories.BudgetRepository;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteBudgetCommandHandler implements CommandHandler<DeleteBudgetCommand> {
    private final BudgetRepository budgetRepository;

    @Override
    public Budget handle(DeleteBudgetCommand command) {
        Budget budget = budgetRepository.load(command.getAggregateId()).orElseThrow(() ->
                new BudgetNotFoundException("Budget %s was not found.".formatted(command.getAggregateId())));

        budget.delete();

        budgetRepository.saveChanges(budget);

        return budget;
    }

    @Override
    @NonNull
    public Class<DeleteBudgetCommand> getCommandType() {
        return DeleteBudgetCommand.class;
    }
}

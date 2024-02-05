package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.ChangeBudgetDescriptionCommand;
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
public class ChangeBudgetDescriptionCommandHandler implements CommandHandler<ChangeBudgetDescriptionCommand> {
    private final BudgetRepository budgetRepository;

    @Override
    public Budget handle(ChangeBudgetDescriptionCommand command) {
        Budget budget = budgetRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new BudgetNotFoundException("Budget %s was not found.".formatted(command.getAggregateId())));

        if(command.getDescription().equals(budget.getDescription().getValue())) return budget;

        budget.changeDescription(command.getDescription());

        budgetRepository.saveChanges(budget);

        return budget;
    }

    @Override
    @NonNull
    public Class<ChangeBudgetDescriptionCommand> getCommandType() {
        return ChangeBudgetDescriptionCommand.class;
    }
}

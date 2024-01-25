package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.CreateBudgetCommand;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetAlreadyExistsException;
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
public class CreateBudgetCommandHandler implements CommandHandler<CreateBudgetCommand> {
    private final BudgetRepository budgetRepository;
    private final CheckUniqueBudgetName checkUniqueBudgetName;

    @Override
    public Budget handle(CreateBudgetCommand command) {
        if(checkUniqueBudgetName.exists(command.getName(), command.getUserId()))
            throw new BudgetAlreadyExistsException("Budget with name %s already exists.".formatted(command.getName()));

        Budget budget = Budget.create(
                command.getUserId(),
                command.getName(),
                command.getDescription(),
                command.getCurrencyCode(),
                command.getUserId()
        );

        budgetRepository.saveChanges(budget);

        return budget;
    }

    @Override
    @NonNull
    public Class<CreateBudgetCommand> getCommandType() {
        return CreateBudgetCommand.class;
    }
}

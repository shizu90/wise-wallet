package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.AddBudgetItemCommand;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetNotFoundException;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.repositories.BudgetRepository;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.currency.domain.models.Currency;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBudgetItemCommandHandler implements CommandHandler<AddBudgetItemCommand> {
    private final BudgetRepository budgetRepository;
    private final CurrencyConversion currencyConversion;

    @Override
    public Budget handle(AddBudgetItemCommand command) {
        Budget budget = budgetRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new BudgetNotFoundException("Budget %s was not found.".formatted(command.getAggregateId())));

        budget.addItem(command.getBillId(), command.getName(), command.getAmount(), command.getCurrencyCode(), command.getType());

        if(!command.getCurrencyCode().equals(budget.getAmount().getCurrencyCode())) {
            Currency budgetItemConvertedAmount = currencyConversion.convert(
                    Currency.create(command.getAmount(), command.getCurrencyCode()),
                    budget.getAmount().getCurrencyCode()
            );

            Currency budgetUpdatedAmount = command.getType().equals("EXPENSE") ?
                    budget.getAmount().add(budgetItemConvertedAmount) :
                    budget.getAmount().subtract(budgetItemConvertedAmount);

            budget.updateAmount(budgetUpdatedAmount.getValue());
        }

        budgetRepository.saveChanges(budget);

        return budget;
    }

    @Override
    @NonNull
    public Class<AddBudgetItemCommand> getCommandType() {
        return AddBudgetItemCommand.class;
    }
}

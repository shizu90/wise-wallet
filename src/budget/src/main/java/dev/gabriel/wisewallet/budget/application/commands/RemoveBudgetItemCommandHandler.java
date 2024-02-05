package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.RemoveBudgetItemCommand;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetNotFoundException;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.models.BudgetItem;
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
public class RemoveBudgetItemCommandHandler implements CommandHandler<RemoveBudgetItemCommand> {
    private final BudgetRepository budgetRepository;
    private final CurrencyConversion currencyConversion;

    @Override
    public Budget handle(RemoveBudgetItemCommand command) {
        Budget budget = budgetRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new BudgetNotFoundException("Budget %s was not found.".formatted(command.getAggregateId())));

        BudgetItem removedItem = budget.removeItem(command.getBillId());

        if(!removedItem.getCurrencyCode().equals(budget.getAmount().getCurrencyCode())) {
            Currency budgetItemConvertedAmount = currencyConversion.convert(
                    Currency.create(removedItem.getAmount(), removedItem.getCurrencyCode()),
                    budget.getAmount().getCurrencyCode()
            );

            Currency budgetUpdatedAmount = removedItem.getCurrencyCode().equals("EXPENSE") ?
                    budget.getAmount().subtract(budgetItemConvertedAmount) :
                    budget.getAmount().add(budgetItemConvertedAmount);

            budget.updateAmount(budgetUpdatedAmount.getValue());
        }

        budgetRepository.saveChanges(budget);

        return budget;
    }

    @Override
    @NonNull
    public Class<RemoveBudgetItemCommand> getCommandType() {
        return RemoveBudgetItemCommand.class;
    }
}

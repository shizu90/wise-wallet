package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.UpdateBudgetAmountCommand;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetNotFoundException;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.repositories.BudgetRepository;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateBudgetAmountCommandHandler implements CommandHandler<UpdateBudgetAmountCommand> {
    private final BudgetRepository budgetRepository;
    private final CurrencyConversion currencyConversion;

    @Override
    @NonNull
    public Budget handle(@NonNull UpdateBudgetAmountCommand command) {
        Budget budget = budgetRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new BudgetNotFoundException("Budget %s was not found.".formatted(command.getAggregateId())));

        int changes = budget.getChanges().size();

        if(!command.getAmount().equals(budget.getAmount().getValue()))
            budget.updateAmount(command.getAmount());

        if(!command.getCurrencyCode().equals(budget.getAmount().getCurrencyCode())) {
            budget.changeCurrencyCode(command.getCurrencyCode());
            budget.updateAmount(currencyConversion.convert(budget.getAmount(), command.getCurrencyCode()).getValue());
        }

        if(budget.getChanges().size() != changes) {
            budgetRepository.saveChanges(budget);
        }

        return budget;
    }

    @Override
    @NonNull
    public Class<UpdateBudgetAmountCommand> getCommandType() {
        return UpdateBudgetAmountCommand.class;
    }
}

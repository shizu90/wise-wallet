package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import dev.gabriel.wisewallet.recurringbill.domain.commands.UpdateRecurringBillAmountCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillNotFoundException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateRecurringBillAmountCommandHandler implements CommandHandler<UpdateRecurringBillAmountCommand> {
    private final RecurringBillRepository recurringBillRepository;
    private final CurrencyConversion currencyConversion;

    @Override
    public RecurringBill handle(@NonNull UpdateRecurringBillAmountCommand command) {
        RecurringBill recurringBill = recurringBillRepository.load(command.getAggregateId()).orElseThrow(() ->
                new RecurringBillNotFoundException("Recurring bill %s was not found.".formatted(command.getAggregateId())));

        int changes = recurringBill.getChanges().size();

        if(!command.getAmount().equals(recurringBill.getAmount().getValue())) {
            recurringBill.updateAmount(command.getAmount());
        }
        if(!command.getCurrencyCode().equals(recurringBill.getAmount().getCurrencyCode())) {
            recurringBill.changeCurrencyCode(command.getCurrencyCode());
            recurringBill.updateAmount(currencyConversion.convert(recurringBill.getAmount(), command.getCurrencyCode()).getValue());
        }

        if(recurringBill.getChanges().size() != changes) {
            recurringBillRepository.saveChanges(recurringBill);
        }

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<UpdateRecurringBillAmountCommand> getCommandType() {
        return UpdateRecurringBillAmountCommand.class;
    }
}

package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ChangeRecurringBillMaxPeriodsCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillNotFoundException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeRecurringBillMaxPeriodsCommandHandler implements CommandHandler<ChangeRecurringBillMaxPeriodsCommand> {
    private final RecurringBillRepository recurringBillRepository;

    @Override
    public RecurringBill handle(@NonNull ChangeRecurringBillMaxPeriodsCommand command) {
        RecurringBill recurringBill = recurringBillRepository.load(command.getAggregateId()).orElseThrow(() ->
                new RecurringBillNotFoundException("Recurring bill %s was not found.".formatted(command.getAggregateId())));

        if (command.getMaxPeriods().equals(recurringBill.getMaxPeriods().getValue())) return recurringBill;

        recurringBill.changeMaxPeriods(command.getMaxPeriods());

        recurringBillRepository.saveChanges(recurringBill);

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<ChangeRecurringBillMaxPeriodsCommand> getCommandType() {
        return ChangeRecurringBillMaxPeriodsCommand.class;
    }
}

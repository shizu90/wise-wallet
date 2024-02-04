package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ExecuteRecurringBillPeriodCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillNotFoundException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExecuteRecurringBillCommandHandler implements CommandHandler<ExecuteRecurringBillPeriodCommand> {
    private final RecurringBillRepository recurringBillRepository;

    @Override
    @NonNull
    public RecurringBill handle(@NonNull ExecuteRecurringBillPeriodCommand command) {
        RecurringBill recurringBill = recurringBillRepository.load(command.getAggregateId()).orElseThrow(() ->
                new RecurringBillNotFoundException("Recurring bill %s was not found.".formatted(command.getAggregateId())));

        recurringBill.nextPeriod(command.getNumberOfPeriods());

        recurringBillRepository.saveChanges(recurringBill);

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<ExecuteRecurringBillPeriodCommand> getCommandType() {
        return ExecuteRecurringBillPeriodCommand.class;
    }
}

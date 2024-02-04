package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ResetRecurringBillCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillNotFoundException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResetRecurringBillCommandHandler implements CommandHandler<ResetRecurringBillCommand> {
    private final RecurringBillRepository recurringBillRepository;

    @Override
    public RecurringBill handle(@NonNull ResetRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository.load(command.getAggregateId()).orElseThrow(() ->
                new RecurringBillNotFoundException("Recurring bill %s was not found.".formatted(command.getAggregateId())));

        if(recurringBill.getCurrentPeriods().getValue().equals(0L)) return recurringBill;

        recurringBill.reset();

        recurringBillRepository.saveChanges(recurringBill);

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<ResetRecurringBillCommand> getCommandType() {
        return ResetRecurringBillCommand.class;
    }
}

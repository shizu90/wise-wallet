package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ChangeRecurringBillTypeCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillNotFoundException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeRecurringBillTypeCommandHandler implements CommandHandler<ChangeRecurringBillTypeCommand> {
    private final RecurringBillRepository recurringBillRepository;

    @Override
    public RecurringBill handle(@NonNull ChangeRecurringBillTypeCommand command) {
        RecurringBill recurringBill = recurringBillRepository.load(command.getAggregateId()).orElseThrow(() ->
                new RecurringBillNotFoundException("Recurring bill %s was not found.".formatted(command.getAggregateId())));

        if(command.getType().equals(recurringBill.getType())) return recurringBill;

        recurringBill.changeType(command.getType());

        recurringBillRepository.saveChanges(recurringBill);

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<ChangeRecurringBillTypeCommand> getCommandType() {
        return ChangeRecurringBillTypeCommand.class;
    }
}

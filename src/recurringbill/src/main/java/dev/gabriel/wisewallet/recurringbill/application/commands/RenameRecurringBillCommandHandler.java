package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.RenameRecurringBillCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillAlreadyExistsException;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillNotFoundException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import dev.gabriel.wisewallet.recurringbill.domain.services.CheckUniqueRecurringBillName;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RenameRecurringBillCommandHandler implements CommandHandler<RenameRecurringBillCommand> {
    private final RecurringBillRepository recurringBillRepository;
    private final CheckUniqueRecurringBillName checkUniqueRecurringBillName;

    @Override
    public RecurringBill handle(@NonNull RenameRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository.load(command.getAggregateId()).orElseThrow(() ->
                new RecurringBillNotFoundException("Recurring bill %s was not found.".formatted(command.getAggregateId())));

        if(command.getName().equals(recurringBill.getName().getValue())) return recurringBill;

        if(checkUniqueRecurringBillName.exists(command.getName(), recurringBill.getWalletId()))
            throw new RecurringBillAlreadyExistsException("Recurring bill with name %s already exists in wallet %s.".formatted(command.getName(), recurringBill.getWalletId()));

        recurringBill.rename(command.getName());

        recurringBillRepository.saveChanges(recurringBill);

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<RenameRecurringBillCommand> getCommandType() {
        return RenameRecurringBillCommand.class;
    }
}

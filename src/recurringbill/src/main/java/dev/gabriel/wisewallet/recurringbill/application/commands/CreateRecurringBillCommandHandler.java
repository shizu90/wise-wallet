package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.CreateRecurringBillCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillAlreadyExistsException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import dev.gabriel.wisewallet.recurringbill.domain.services.CheckUniqueRecurringBillName;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateRecurringBillCommandHandler implements CommandHandler<CreateRecurringBillCommand> {
    private final RecurringBillRepository recurringBillRepository;
    private final CheckUniqueRecurringBillName checkUniqueRecurringBillName;

    @Override
    public RecurringBill handle(@NonNull CreateRecurringBillCommand command) {
        if(checkUniqueRecurringBillName.exists(command.getName(), command.getWalletId()))
            throw new RecurringBillAlreadyExistsException("Recurring bill with name %s already exists in wallet %s.".formatted(command.getName(), command.getWalletId()));

        RecurringBill recurringBill = RecurringBill.create(
                command.getAggregateId(),
                command.getName(),
                command.getDescription(),
                command.getAmount(),
                command.getCurrencyCode(),
                command.getRecurrence(),
                command.getType(),
                command.getMaxPeriods(),
                command.getWalletId(),
                command.getCategoryId()
        );

        recurringBillRepository.saveChanges(recurringBill);

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<CreateRecurringBillCommand> getCommandType() {
        return CreateRecurringBillCommand.class;
    }
}

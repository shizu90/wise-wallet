package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ChangeRecurringBillDescriptionCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillNotFoundException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeRecurringBillDescriptionCommandHandler  implements CommandHandler<ChangeRecurringBillDescriptionCommand> {
    private final RecurringBillRepository recurringBillRepository;

    @Override
    public RecurringBill handle(@NonNull ChangeRecurringBillDescriptionCommand command) {
        RecurringBill recurringBill = recurringBillRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new RecurringBillNotFoundException("Recurring bill %s was not found.".formatted(command.getAggregateId())));

        if(command.getDescription().equals(recurringBill.getDescription().getValue())) return recurringBill;

        recurringBill.changeDescription(command.getDescription());

        recurringBillRepository.saveChanges(recurringBill);

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<ChangeRecurringBillDescriptionCommand> getCommandType() {
        return ChangeRecurringBillDescriptionCommand.class;
    }
}

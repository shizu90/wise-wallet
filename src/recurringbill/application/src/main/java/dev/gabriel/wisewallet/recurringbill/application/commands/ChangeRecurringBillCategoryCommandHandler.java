package dev.gabriel.wisewallet.recurringbill.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ChangeRecurringBillCategoryCommand;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillNotFoundException;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeRecurringBillCategoryCommandHandler implements CommandHandler<ChangeRecurringBillCategoryCommand> {
    private final RecurringBillRepository recurringBillRepository;

    @Override
    public RecurringBill handle(@NonNull ChangeRecurringBillCategoryCommand command) {
        RecurringBill recurringBill = recurringBillRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new RecurringBillNotFoundException("Recurring bill %s was not found.".formatted(command.getAggregateId())));

        if(command.getCategoryId().equals(recurringBill.getCategoryId())) return recurringBill;

        recurringBill.changeCategory(command.getCategoryId());

        recurringBillRepository.saveChanges(recurringBill);

        return recurringBill;
    }

    @Override
    @NonNull
    public Class<ChangeRecurringBillCategoryCommand> getCommandType() {
        return ChangeRecurringBillCategoryCommand.class;
    }
}

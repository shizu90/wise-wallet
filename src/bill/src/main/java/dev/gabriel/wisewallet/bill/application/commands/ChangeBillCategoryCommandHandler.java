package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.ChangeBillCategoryCommand;
import dev.gabriel.wisewallet.bill.domain.exceptions.BillNotFoundException;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeBillCategoryCommandHandler implements CommandHandler<ChangeBillCategoryCommand> {
    private final BillRepository billRepository;

    @Override
    public Bill handle(@NonNull ChangeBillCategoryCommand command) {
        Bill bill = billRepository.load(command.getAggregateId()).orElseThrow(() ->
                new BillNotFoundException("Bill %s was not found.".formatted(command.getAggregateId())));

        if(command.getCategoryId().equals(bill.getCategoryId())) return bill;

        bill.changeCategory(command.getCategoryId());

        billRepository.saveChanges(bill);

        return bill;
    }

    @Override
    @NonNull
    public Class<ChangeBillCategoryCommand> getCommandType() {
        return ChangeBillCategoryCommand.class;
    }
}

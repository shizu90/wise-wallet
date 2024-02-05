package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.ChangeBillTypeCommand;
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
public class ChangeBillTypeCommandHandler implements CommandHandler<ChangeBillTypeCommand> {
    private final BillRepository billRepository;

    @Override
    public Bill handle(@NonNull ChangeBillTypeCommand command) {
        Bill bill = billRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new BillNotFoundException("Bill %s was not found.".formatted(command.getAggregateId())));

        if(command.getType().equals(bill.getType())) return bill;

        bill.changeType(command.getType());

        billRepository.saveChanges(bill);

        return bill;
    }

    @Override
    @NonNull
    public Class<ChangeBillTypeCommand> getCommandType() {
        return ChangeBillTypeCommand.class;
    }
}

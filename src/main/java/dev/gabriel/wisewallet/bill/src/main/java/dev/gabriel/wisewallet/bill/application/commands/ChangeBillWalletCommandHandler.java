package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.ChangeBillWalletCommand;
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
public class ChangeBillWalletCommandHandler implements CommandHandler<ChangeBillWalletCommand> {
    private final BillRepository billRepository;

    @Override
    public Bill handle(ChangeBillWalletCommand command) {
        Bill bill = billRepository.load(command.getAggregateId()).orElseThrow(() ->
                new BillNotFoundException("Bill %s was not found.".formatted(command.getAggregateId())));

        if(command.getWalletId().equals(bill.getWalletId())) return bill;

        bill.changeWallet(command.getWalletId());

        billRepository.saveChanges(bill);

        return bill;
    }

    @Override
    @NonNull
    public Class<ChangeBillWalletCommand> getCommandType() {
        return ChangeBillWalletCommand.class;
    }
}

package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.CreateBillCommand;
import dev.gabriel.wisewallet.bill.domain.exceptions.BillAlreadyExistsException;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
import dev.gabriel.wisewallet.bill.domain.services.CheckUniqueBillName;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateBillCommandHandler implements CommandHandler<CreateBillCommand> {
    private final BillRepository billRepository;
    private final CheckUniqueBillName checkUniqueBillName;

    @Override
    public Bill handle(@NonNull CreateBillCommand command) {
        if(checkUniqueBillName.exists(command.getName(), command.getWalletId()))
            throw new BillAlreadyExistsException("Bill with name %s already exists in wallet %s.".formatted(command.getName(), command.getWalletId()));

        Bill bill = Bill.create(
                command.getAggregateId(),
                command.getName(),
                command.getDescription(),
                command.getAmount(),
                command.getCurrencyCode(),
                command.getType(),
                command.getWalletId(),
                command.getCategoryId()
        );

        billRepository.saveChanges(bill);

        return bill;
    }

    @Override
    @NonNull
    public Class<CreateBillCommand> getCommandType() {
        return CreateBillCommand.class;
    }
}

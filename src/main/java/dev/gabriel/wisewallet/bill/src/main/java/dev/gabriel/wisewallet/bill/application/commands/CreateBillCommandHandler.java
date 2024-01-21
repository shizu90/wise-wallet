package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.CreateBillCommand;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateBillCommandHandler implements CommandHandler<CreateBillCommand> {
    private final BillRepository billRepository;

    @Override
    public Bill handle(@NonNull CreateBillCommand command) {
        Bill bill = Bill.create(
                UUID.randomUUID(),
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

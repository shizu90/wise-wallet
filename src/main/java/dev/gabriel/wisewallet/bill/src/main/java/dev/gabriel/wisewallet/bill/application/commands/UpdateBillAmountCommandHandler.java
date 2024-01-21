package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.UpdateBillAmountCommand;
import dev.gabriel.wisewallet.bill.domain.exceptions.BillNotFoundException;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateBillAmountCommandHandler implements CommandHandler<UpdateBillAmountCommand> {
    private final BillRepository billRepository;
    private final CurrencyConversion currencyConversion;

    @Override
    public Bill handle(@NonNull UpdateBillAmountCommand command) {
        Bill bill = billRepository.load(command.getAggregateId()).orElseThrow(() ->
                new BillNotFoundException("Bill %s was not found.".formatted(command.getAggregateId())));

        int changes = bill.getChanges().size();

        if(!command.getAmount().equals(bill.getAmount().getValue()))
            bill.updateAmount(command.getAmount());

        if(!command.getCurrencyCode().equals(bill.getAmount().getCurrencyCode())) {
            bill.changeCurrencyCode(command.getCurrencyCode());
            bill.updateAmount(currencyConversion.convert(bill.getAmount(), command.getCurrencyCode()).getValue());
        }

        if(bill.getChanges().size() != changes)
            billRepository.saveChanges(bill);

        return bill;
    }

    @Override
    @NonNull
    public Class<UpdateBillAmountCommand> getCommandType() {
        return UpdateBillAmountCommand.class;
    }
}

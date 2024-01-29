package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.currency.domain.models.Currency;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import dev.gabriel.wisewallet.wallet.domain.commands.SubtractAmountCommand;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubtractAmountCommandHandler implements CommandHandler<SubtractAmountCommand> {
    private final WalletRepository walletRepository;
    private final CurrencyConversion currencyConversion;

    @Override
    public Wallet handle(SubtractAmountCommand command) {
        Wallet wallet = walletRepository.load(command.getAggregateId()).orElseThrow(() ->
                new WalletNotFoundException("Wallet %s was not found.".formatted(command.getAggregateId())));

        Currency amountToSubtract = Currency.create(command.getAmount(), command.getCurrencyCode());
        if(command.getCurrencyCode().equals(wallet.getBalance().getCurrencyCode())) {
            wallet.updateBalance(wallet.getBalance().subtract(amountToSubtract).getValue());
        }else {
            Currency convertedAmount = currencyConversion.convert(
                    amountToSubtract,
                    wallet.getBalance().getCurrencyCode()
            );

            wallet.updateBalance(wallet.getBalance().subtract(convertedAmount).getValue());
        }

        walletRepository.saveChanges(wallet);

        return wallet;
    }

    @Override
    @NonNull
    public Class<SubtractAmountCommand> getCommandType() {
        return SubtractAmountCommand.class;
    }
}

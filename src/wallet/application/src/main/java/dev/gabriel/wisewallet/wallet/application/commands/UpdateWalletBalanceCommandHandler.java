package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import dev.gabriel.wisewallet.wallet.domain.commands.UpdateWalletBalanceCommand;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateWalletBalanceCommandHandler implements CommandHandler<UpdateWalletBalanceCommand> {
    private final WalletRepository walletRepository;
    private final CurrencyConversion currencyConversion;

    @Override
    public Wallet handle(@NonNull UpdateWalletBalanceCommand command) {
        Wallet wallet = walletRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new WalletNotFoundException("Wallet %s was not found.".formatted(command.getAggregateId())));

        int changes = wallet.getChanges().size();

        if(command.getBalance() != null && !command.getBalance().equals(wallet.getBalance().getValue()))
            wallet.updateBalance(command.getBalance());

        if(command.getCurrencyCode() != null && !command.getCurrencyCode().equals(wallet.getBalance().getCurrencyCode())) {
            wallet.changeCurrencyCode(command.getCurrencyCode());
            wallet.updateBalance(currencyConversion.convert(wallet.getBalance(), command.getCurrencyCode()).getValue());
        }

        if(wallet.getChanges().size() > changes)
            walletRepository.saveChanges(wallet);

        return wallet;
    }

    @Override
    @NonNull
    public Class<UpdateWalletBalanceCommand> getCommandType() {
        return UpdateWalletBalanceCommand.class;
    }
}

package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.domain.commands.UpdateWalletBalanceCommand;
import dev.gabriel.wisewallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.domain.models.Wallet;
import dev.gabriel.wisewallet.domain.repositories.WalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateWalletBalanceCommandHandler implements CommandHandler<UpdateWalletBalanceCommand> {
    private final WalletRepository walletRepository;

    @Override
    public Wallet handle(UpdateWalletBalanceCommand command) {
        Wallet wallet = walletRepository.load(command.getAggregateId()).orElseThrow(() ->
                new WalletNotFoundException("Wallet %s was not found.".formatted(command.getAggregateId())));

        long changesCount = wallet.getChanges().size();

        if(command.getBalance() != null && !command.getBalance().equals(wallet.getBalance().getValue()))
            wallet.updateBalance(command.getBalance());

        if(command.getCurrencyCode() != null && !command.getCurrencyCode().equals(wallet.getBalance().getCurrencyCode()))
            wallet.changeCurrencyCode(command.getCurrencyCode());

        if(wallet.getChanges().size() != changesCount)
            walletRepository.saveChanges(wallet);

        return wallet;
    }

    @Override
    @NonNull
    public Class<UpdateWalletBalanceCommand> getCommandType() {
        return UpdateWalletBalanceCommand.class;
    }
}

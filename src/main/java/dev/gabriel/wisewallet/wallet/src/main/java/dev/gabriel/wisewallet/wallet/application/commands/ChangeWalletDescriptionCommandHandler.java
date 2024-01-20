package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.wallet.domain.commands.ChangeWalletDescriptionCommand;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeWalletDescriptionCommandHandler implements CommandHandler<ChangeWalletDescriptionCommand> {
    private final WalletRepository walletRepository;

    @Override
    public Wallet handle(ChangeWalletDescriptionCommand command) {
        Wallet wallet = walletRepository.load(command.getAggregateId()).orElseThrow(() ->
                new WalletNotFoundException("Wallet %s was not found.".formatted(command.getAggregateId())));

        if(command.getDescription() != null && !command.getDescription().equals(wallet.getDescription().getValue())) {
            wallet.changeDescription(command.getDescription());
            walletRepository.saveChanges(wallet);
        }

        return wallet;
    }

    @Override
    @NonNull
    public Class<ChangeWalletDescriptionCommand> getCommandType() {
        return ChangeWalletDescriptionCommand.class;
    }
}

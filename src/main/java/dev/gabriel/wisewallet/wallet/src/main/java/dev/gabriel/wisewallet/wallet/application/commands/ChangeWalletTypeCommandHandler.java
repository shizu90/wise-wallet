package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.wallet.domain.commands.ChangeWalletTypeCommand;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeWalletTypeCommandHandler implements CommandHandler<ChangeWalletTypeCommand> {
    private final WalletRepository walletRepository;

    @Override
    public Wallet handle(ChangeWalletTypeCommand command) {
        Wallet wallet = walletRepository.load(command.getAggregateId()).orElseThrow(() ->
                new WalletNotFoundException("Wallet %s was not found.".formatted(command.getAggregateId())));

        if(command.getType() != null && !command.getType().equals(wallet.getType())) {
            wallet.changeType(command.getType());
            walletRepository.saveChanges(wallet);
        }

        return wallet;
    }

    @Override
    @NonNull
    public Class<ChangeWalletTypeCommand> getCommandType() {
        return ChangeWalletTypeCommand.class;
    }
}

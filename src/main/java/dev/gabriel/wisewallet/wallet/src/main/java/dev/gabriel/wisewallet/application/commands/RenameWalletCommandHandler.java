package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.domain.commands.RenameWalletCommand;
import dev.gabriel.wisewallet.domain.exceptions.WalletAlreadyExistsException;
import dev.gabriel.wisewallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.domain.models.Wallet;
import dev.gabriel.wisewallet.domain.repositories.WalletRepository;
import dev.gabriel.wisewallet.domain.services.CheckUniqueWalletName;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RenameWalletCommandHandler implements CommandHandler<RenameWalletCommand> {
    private final WalletRepository walletRepository;
    private final CheckUniqueWalletName checkUniqueWalletName;

    @Override
    public Wallet handle(RenameWalletCommand command) {
        Wallet wallet = walletRepository.load(command.getAggregateId()).orElseThrow(() ->
                new WalletNotFoundException("Wallet %s was not found.".formatted(command.getAggregateId())));

        if(command.getName() != null && command.getName().equals(wallet.getName().getValue())) return wallet;

        if(checkUniqueWalletName.exists(wallet.getUserId(), command.getName()) >= 1)
            throw new WalletAlreadyExistsException("Wallet with name %s already exists.".formatted(command.getName()));

        wallet.rename(command.getName());

        walletRepository.saveChanges(wallet);

        return wallet;
    }

    @Override
    @NonNull
    public Class<RenameWalletCommand> getCommandType() {
        return RenameWalletCommand.class;
    }
}

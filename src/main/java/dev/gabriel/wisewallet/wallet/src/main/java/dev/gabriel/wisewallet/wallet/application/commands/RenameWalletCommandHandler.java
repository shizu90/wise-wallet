package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.wallet.domain.commands.RenameWalletCommand;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletAlreadyExistsException;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import dev.gabriel.wisewallet.wallet.domain.services.CheckUniqueWalletName;
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
    public Wallet handle(@NonNull RenameWalletCommand command) {
        Wallet wallet = walletRepository.load(command.getAggregateId()).orElseThrow(() ->
                new WalletNotFoundException("Wallet %s was not found.".formatted(command.getAggregateId())));

        if(wallet.getName().getValue().equals(command.getName())) return wallet;

        if(checkUniqueWalletName.exists(command.getName(), wallet.getUserId()))
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

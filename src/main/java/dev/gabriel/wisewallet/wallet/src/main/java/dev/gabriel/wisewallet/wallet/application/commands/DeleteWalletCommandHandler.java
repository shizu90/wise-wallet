package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.wallet.domain.commands.DeleteWalletCommand;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteWalletCommandHandler implements CommandHandler<DeleteWalletCommand> {
    private final WalletRepository walletRepository;

    @Override
    public Wallet handle(@NonNull DeleteWalletCommand command) {
        Wallet wallet = walletRepository.load(command.getAggregateId()).orElseThrow(() ->
                new WalletNotFoundException("Wallet %s was not found.".formatted(command.getAggregateId())));

        wallet.delete();

        walletRepository.saveChanges(wallet);

        return wallet;
    }

    @Override
    @NonNull
    public Class<DeleteWalletCommand> getCommandType() {
        return DeleteWalletCommand.class;
    }
}

package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.domain.commands.CreateWalletCommand;
import dev.gabriel.wisewallet.domain.exceptions.WalletAlreadyExistsException;
import dev.gabriel.wisewallet.domain.models.Wallet;
import dev.gabriel.wisewallet.domain.repositories.WalletRepository;
import dev.gabriel.wisewallet.domain.services.CheckUniqueWalletName;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateWalletCommandHandler implements CommandHandler<CreateWalletCommand> {
    private final WalletRepository walletRepository;
    private final CheckUniqueWalletName checkUniqueWalletNameService;

    @Override
    public Wallet handle(CreateWalletCommand command) {
        if(checkUniqueWalletNameService.exists(command.getUserId(), command.getName()) >= 1)
            throw new WalletAlreadyExistsException("Wallet with name %s already exists.".formatted(command.getName()));

        Wallet wallet = Wallet.create(
                UUID.randomUUID(),
                command.getName(),
                command.getDescription(),
                command.getBalance(),
                command.getCurrencyCode(),
                command.getMain(),
                command.getType(),
                command.getUserId()
        );

        walletRepository.saveChanges(wallet);

        return wallet;
    }

    @Override
    @NonNull
    public Class<CreateWalletCommand> getCommandType() {
        return CreateWalletCommand.class;
    }
}

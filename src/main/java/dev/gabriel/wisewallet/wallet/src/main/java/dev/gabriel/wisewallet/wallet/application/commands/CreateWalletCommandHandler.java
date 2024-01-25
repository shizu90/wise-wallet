package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.wallet.domain.commands.CreateWalletCommand;
import dev.gabriel.wisewallet.wallet.domain.exceptions.ReachedMaxWalletsException;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletAlreadyExistsException;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import dev.gabriel.wisewallet.wallet.domain.services.CheckUniqueWalletName;
import dev.gabriel.wisewallet.wallet.domain.services.CheckUserWallets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateWalletCommandHandler implements CommandHandler<CreateWalletCommand> {
    private final WalletRepository walletRepository;
    private final CheckUniqueWalletName checkUniqueWalletNameService;
    private final CheckUserWallets checkUserWallets;

    @Override
    public Wallet handle(@NonNull CreateWalletCommand command) {
        if(checkUniqueWalletNameService.exists(command.getUserId(), command.getName()) >= 1)
            throw new WalletAlreadyExistsException("Wallet with name %s already exists.".formatted(command.getName()));

        if(checkUserWallets.getSize(command.getUserId()) == 10)
            throw new ReachedMaxWalletsException("User %s reached max number of wallets.".formatted(command.getUserId()));

        Wallet wallet = Wallet.create(
                command.getAggregateId(),
                command.getName(),
                command.getDescription(),
                command.getBalance(),
                command.getCurrencyCode(),
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

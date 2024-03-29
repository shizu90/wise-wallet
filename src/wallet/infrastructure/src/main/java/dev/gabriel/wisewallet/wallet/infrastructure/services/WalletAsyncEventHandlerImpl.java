package dev.gabriel.wisewallet.wallet.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
import dev.gabriel.wisewallet.wallet.application.events.WalletAsyncEventHandler;
import dev.gabriel.wisewallet.wallet.domain.commands.DeleteWalletCommand;
import dev.gabriel.wisewallet.wallet.domain.commands.WalletCommand;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjection;
import dev.gabriel.wisewallet.wallet.infrastructure.projection.WalletProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletAsyncEventHandlerImpl implements WalletAsyncEventHandler {
    private final CommandBus<WalletCommand> commandBus;
    private final WalletProjectionRepository walletProjectionRepository;

    @Override
    public void handle(UserDeletedEvent event) {
        List<WalletProjection> wallets = walletProjectionRepository.findByUserId(event.getAggregateId());

        for(WalletProjection wallet : wallets) {
            commandBus.execute(new DeleteWalletCommand(wallet.getId()));
        }
    }
}

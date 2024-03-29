package dev.gabriel.wisewallet.wallet.application.events;

import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;

public interface WalletAsyncEventHandler {
    void handle(UserDeletedEvent event);
}

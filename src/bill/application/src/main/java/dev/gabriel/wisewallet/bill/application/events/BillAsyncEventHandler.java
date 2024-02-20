package dev.gabriel.wisewallet.bill.application.events;

import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.wallet.domain.events.WalletDeletedEvent;

public interface BillAsyncEventHandler {
    void handle(CategoryDeletedEvent event);
    void handle(WalletDeletedEvent event);
}
package dev.gabriel.wisewallet.recurringbill.application.events;

import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.wallet.domain.events.WalletDeletedEvent;

public interface RecurringBillAsyncEventHandler {
    void handle(CategoryDeletedEvent event);
    void handle(WalletDeletedEvent event);
}

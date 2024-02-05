package dev.gabriel.wisewallet.bill.application.events;

import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.wallet.domain.events.WalletDeletedEvent;
import org.springframework.kafka.support.Acknowledgment;

public interface BillAsyncEventHandler {
    void handle(CategoryDeletedEvent event, Acknowledgment ack);
    void handle(WalletDeletedEvent event, Acknowledgment ack);
}
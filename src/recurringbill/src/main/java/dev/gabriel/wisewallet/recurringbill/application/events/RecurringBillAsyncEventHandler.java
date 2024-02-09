package dev.gabriel.wisewallet.recurringbill.application.events;

import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.wallet.domain.events.WalletDeletedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface RecurringBillAsyncEventHandler {
    void handle(@Payload CategoryDeletedEvent event, Acknowledgment ack);
    void handle(@Payload WalletDeletedEvent event, Acknowledgment ack);
}

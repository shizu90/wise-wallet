package dev.gabriel.wisewallet.wallet.application.events;

import dev.gabriel.wisewallet.bill.domain.events.BillAmountUpdatedEvent;
import dev.gabriel.wisewallet.bill.domain.events.BillCreatedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface WalletAsyncEventHandler {
    void handle(@Payload BillCreatedEvent event, Acknowledgment ack);
    void handle(@Payload BillAmountUpdatedEvent event, Acknowledgment ack);
}

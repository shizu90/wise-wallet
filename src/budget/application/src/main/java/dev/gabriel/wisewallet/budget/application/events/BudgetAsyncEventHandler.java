package dev.gabriel.wisewallet.budget.application.events;

import dev.gabriel.wisewallet.bill.domain.events.*;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface BudgetAsyncEventHandler {
    void handle(@Payload BillAmountUpdatedEvent event, Acknowledgment ack);
    void handle(@Payload BillTypeChangedEvent event, Acknowledgment ack);
    void handle(@Payload BillDeletedEvent event, Acknowledgment ack);
    void handle(@Payload BillCurrencyCodeChangedEvent event, Acknowledgment ack);
    void handle(@Payload BillRenamedEvent event, Acknowledgment ack);
}

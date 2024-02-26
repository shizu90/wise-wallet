package dev.gabriel.wisewallet.budget.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.bill.domain.events.*;
import dev.gabriel.wisewallet.budget.application.events.BudgetAsyncEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetEventConsumer {
    private final BudgetAsyncEventHandler budgetAsyncEventHandler;
    private final ObjectMapper objectMapper;
    private static final String GROUP_ID = "budget-consumer";

    @SneakyThrows
    @KafkaListener(topics = "BillAmountUpdatedEvent", groupId = GROUP_ID)
    public void handleBillAmountUpdatedEvent(@Payload String event) {
        budgetAsyncEventHandler.handle(objectMapper.readValue(event, BillAmountUpdatedEvent.class));
    }

    @SneakyThrows
    @KafkaListener(topics = "BillCurrencyCodeChangedEvent", groupId = GROUP_ID)
    public void handleBillCurrencyCodeChangedEvent(@Payload String event) {
        budgetAsyncEventHandler.handle(objectMapper.readValue(event, BillCurrencyCodeChangedEvent.class));
    }

    @SneakyThrows
    @KafkaListener(topics = "BillTypeChangedEvent", groupId = GROUP_ID)
    public void handleBillTypeChangedEvent(@Payload String event) {
        budgetAsyncEventHandler.handle(objectMapper.readValue(event, BillTypeChangedEvent.class));
    }

    @SneakyThrows
    @KafkaListener(topics = "BillRenamedEvent", groupId = GROUP_ID)
    public void handleBillRenamedEvent(@Payload String event) {
        budgetAsyncEventHandler.handle(objectMapper.readValue(event, BillRenamedEvent.class));
    }

    @SneakyThrows
    @KafkaListener(topics = "BillDeletedEvent", groupId = GROUP_ID)
    public void handleBillDeletedEvent(@Payload String event) {
        budgetAsyncEventHandler.handle(objectMapper.readValue(event, BillDeletedEvent.class));
    }
}

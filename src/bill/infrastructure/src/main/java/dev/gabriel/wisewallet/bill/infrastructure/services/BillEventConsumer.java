package dev.gabriel.wisewallet.bill.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.bill.application.events.BillAsyncEventHandler;
import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.wallet.domain.events.WalletDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillEventConsumer {
    private final BillAsyncEventHandler billAsyncEventHandler;
    private final ObjectMapper objectMapper;
    private static final String GROUP_ID = "bill-consumer";

    @SneakyThrows
    @KafkaListener(topics = "CategoryDeletedEvent", groupId = GROUP_ID)
    public void handleCategoryDeletedEvent(@Payload String event) {
        billAsyncEventHandler.handle(objectMapper.readValue(event, CategoryDeletedEvent.class));
    }

    @SneakyThrows
    @KafkaListener(topics = "WalletDeletedEvent", groupId = GROUP_ID)
    public void handleWalletDeletedEvent(@Payload String event) {
        billAsyncEventHandler.handle(objectMapper.readValue(event, WalletDeletedEvent.class));
    }
}
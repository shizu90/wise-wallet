package dev.gabriel.wisewallet.recurringbill.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.recurringbill.application.events.RecurringBillAsyncEventHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ChangeRecurringBillCategoryCommand;
import dev.gabriel.wisewallet.recurringbill.domain.commands.DeleteRecurringBillCommand;
import dev.gabriel.wisewallet.recurringbill.domain.commands.RecurringBillCommand;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjection;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjectionRepository;
import dev.gabriel.wisewallet.wallet.domain.events.WalletDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecurringBillEventConsumer {
    private final RecurringBillAsyncEventHandler recurringBillAsyncEventHandler;
    private final ObjectMapper objectMapper;
    private static final String GROUP_ID = "recurring-bill-consumer";

    @SneakyThrows
    @KafkaListener(topics = "CategoryDeletedEvent", groupId = GROUP_ID)
    public void handleCategoryDeletedEvent(@Payload String event) {
        recurringBillAsyncEventHandler.handle(objectMapper.readValue(event, CategoryDeletedEvent.class));
    }

    @SneakyThrows
    @KafkaListener(topics = "WalletDeletedEvent", groupId = GROUP_ID)
    public void handleWalletDeletedEvent(@Payload String event) {
        recurringBillAsyncEventHandler.handle(objectMapper.readValue(event, WalletDeletedEvent.class));
    }
}

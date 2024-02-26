package dev.gabriel.wisewallet.category.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.category.application.events.CategoryAsyncEventHandler;
import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryEventConsumer {
    private final CategoryAsyncEventHandler categoryAsyncEventHandler;
    private final ObjectMapper objectMapper;
    private static final String GROUP_ID = "category-consumer";

    @SneakyThrows
    @KafkaListener(topics = "UserDeletedEvent", groupId = GROUP_ID)
    public void handleUserDeletedEvent(@Payload String event) {
        categoryAsyncEventHandler.handle(objectMapper.readValue(event, UserDeletedEvent.class));
    }
}

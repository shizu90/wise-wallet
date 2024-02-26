package dev.gabriel.wisewallet.reminder.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.reminder.application.events.ReminderAsyncEventHandler;
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
public class ReminderEventConsumer {
    private final ReminderAsyncEventHandler reminderAsyncEventHandler;
    private final ObjectMapper objectMapper;
    private static final String GROUP_ID = "reminder-consumer";

    @SneakyThrows
    @KafkaListener(topics = "UserDeletedEvent", groupId = GROUP_ID)
    public void handleUserDeletedEvent(@Payload String event) {
        reminderAsyncEventHandler.handle(objectMapper.readValue(event, UserDeletedEvent.class));
    }
}

package dev.gabriel.wisewallet.reminder.application.events;

import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface ReminderAsyncEventHandler {
    void handle(@Payload UserDeletedEvent event, Acknowledgment ack);
}

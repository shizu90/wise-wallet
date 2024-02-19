package dev.gabriel.wisewallet.wallet.application.events;

import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface WalletAsyncEventHandler {
    void handle(@Payload UserDeletedEvent event, Acknowledgment ack);
}

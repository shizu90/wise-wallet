package dev.gabriel.wisewallet.wallet.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
import dev.gabriel.wisewallet.wallet.application.events.WalletAsyncEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletEventConsumer {
    private final WalletAsyncEventHandler walletAsyncEventHandler;
    private final ObjectMapper objectMapper;
    private static final String GROUP_ID = "wallet-consumer";

    @SneakyThrows
    @KafkaListener(topics = "UserDeletedEvent", groupId = GROUP_ID)
    public void handle(@Payload String event) {
        System.out.println(event);
        UserDeletedEvent userDeletedEvent = objectMapper.readValue(event, UserDeletedEvent.class);
        walletAsyncEventHandler.handle(userDeletedEvent);
    }
}

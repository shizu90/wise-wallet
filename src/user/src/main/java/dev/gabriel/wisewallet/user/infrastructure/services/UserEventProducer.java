package dev.gabriel.wisewallet.user.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.infrastructure.services.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEventProducer implements EventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void publish(String topic, DomainEvent event) {
        kafkaTemplate.send(topic, event);
    }
}

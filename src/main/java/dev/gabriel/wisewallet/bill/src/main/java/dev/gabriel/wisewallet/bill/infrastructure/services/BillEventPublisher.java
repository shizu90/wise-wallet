package dev.gabriel.wisewallet.bill.infrastructure.services;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.infrastructure.services.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillEventPublisher implements EventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Override
    public void publish(String topic, DomainEvent event) {
        kafkaTemplate.send(topic, event);
    }
}

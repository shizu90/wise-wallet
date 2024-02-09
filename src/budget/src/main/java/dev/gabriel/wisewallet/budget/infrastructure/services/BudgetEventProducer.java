package dev.gabriel.wisewallet.budget.infrastructure.services;

import dev.gabriel.wisewallet.budget.domain.events.EventType;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.infrastructure.services.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetEventProducer implements EventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private static final List<EventType> AVAILABLE_TOPICS = List.of();

    @Override
    public void publish(String topic, DomainEvent event) {
        boolean topicAvailability = AVAILABLE_TOPICS
                .stream()
                .anyMatch(e -> e.equals(EventType.valueOf(event.getEventType())));

        if(topicAvailability) kafkaTemplate.send(topic, event);
    }
}

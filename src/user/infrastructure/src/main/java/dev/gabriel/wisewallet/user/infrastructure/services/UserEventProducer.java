package dev.gabriel.wisewallet.user.infrastructure.services;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.infrastructure.services.EventPublisher;
import dev.gabriel.wisewallet.user.domain.events.EventType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEventProducer implements EventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private static final List<EventType> AVAILABLE_TOPICS = List.of(new EventType[]{
            EventType.USER_DELETED,
            EventType.USER_CREATED,
            EventType.USER_EMAIL_CHANGED,
            EventType.USER_PASSWORD_CHANGED
    });

    @Override
    @SneakyThrows
    public void publish(String topic, DomainEvent event) {
        boolean topicAvailability = AVAILABLE_TOPICS
                .stream()
                .anyMatch(e -> e.equals(EventType.valueOf(event.getEventType())));

        if(topicAvailability) kafkaTemplate.send(topic, event);
    }
}

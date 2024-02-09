package dev.gabriel.wisewallet.bill.infrastructure.services;

import dev.gabriel.wisewallet.bill.domain.events.EventType;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.infrastructure.services.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillEventProducer implements EventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private static final List<EventType> AVAILABLE_TOPICS = List.of(new EventType[]{
            EventType.BILL_AMOUNT_UPDATED,
            EventType.BILL_RENAMED,
            EventType.BILL_DELETED,
            EventType.BILL_CURRENCY_CODE_CHANGED,
            EventType.BILL_TYPE_CHANGED
    });

    @Override
    public void publish(String topic, DomainEvent event) {
        boolean topicAvailability = AVAILABLE_TOPICS
                .stream()
                .anyMatch(e -> e.equals(EventType.valueOf(event.getEventType())));

        if(topicAvailability) kafkaTemplate.send(topic, event);
    }
}

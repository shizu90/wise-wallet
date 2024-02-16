package dev.gabriel.wisewallet.core.infrastructure.services;

import dev.gabriel.wisewallet.core.application.AsyncEventPublisher;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

public interface EventPublisher extends AsyncEventPublisher {
    void publish(String topic, DomainEvent event);
}

package dev.gabriel.wisewallet.core.infrastructure.services;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

public interface EventPublisher {
    void publish(String topic, DomainEvent event);
}

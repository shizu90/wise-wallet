package dev.gabriel.wisewallet.core.application;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

public interface AsyncEventPublisher {
    void publish(String topic, DomainEvent event);
}

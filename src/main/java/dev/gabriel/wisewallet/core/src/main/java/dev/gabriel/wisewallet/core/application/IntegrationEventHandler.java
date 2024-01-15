package dev.gabriel.wisewallet.core.application;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.events.DomainEventWithId;
import lombok.NonNull;

public interface IntegrationEventHandler {
    void handleEvent(DomainEventWithId<DomainEvent> event);

    @NonNull
    String getAggregateType();
}

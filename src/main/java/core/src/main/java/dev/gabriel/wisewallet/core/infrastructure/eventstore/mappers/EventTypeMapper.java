package dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

public interface EventTypeMapper {
    Class<? extends DomainEvent> getClassByEventType(String eventType);
}

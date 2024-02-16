package dev.gabriel.wisewallet.core.domain.models.mappers;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;

public interface EventTypeMapper {
    Class<? extends DomainEvent> getClassByEventType(String eventType);
}

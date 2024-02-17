package dev.gabriel.wisewallet.category.infrastructure.mappers;

import dev.gabriel.wisewallet.category.domain.events.EventType;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.models.mappers.EventTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryEventTypeMapper implements EventTypeMapper {
    @Override
    public Class<? extends DomainEvent> getClassByEventType(String eventType) {
        return EventType.valueOf(eventType).getType();
    }
}

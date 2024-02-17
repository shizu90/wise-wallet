package dev.gabriel.wisewallet.user.infrastructure.mappers;

import dev.gabriel.wisewallet.core.domain.models.mappers.EventTypeMapper;
import dev.gabriel.wisewallet.user.domain.events.EventType;
import dev.gabriel.wisewallet.user.domain.events.UserEvent;
import org.springframework.stereotype.Component;

@Component
public class UserEventTypeMapper implements EventTypeMapper {
    @Override
    public Class<? extends UserEvent> getClassByEventType(String eventType) {
        return EventType.valueOf(eventType).getType();
    }
}

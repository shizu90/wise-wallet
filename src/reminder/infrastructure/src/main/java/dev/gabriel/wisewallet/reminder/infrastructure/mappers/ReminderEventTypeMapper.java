package dev.gabriel.wisewallet.reminder.infrastructure.mappers;

import dev.gabriel.wisewallet.core.domain.models.mappers.EventTypeMapper;
import dev.gabriel.wisewallet.reminder.domain.events.EventType;
import dev.gabriel.wisewallet.reminder.domain.events.ReminderEvent;
import org.springframework.stereotype.Component;

@Component
public class ReminderEventTypeMapper implements EventTypeMapper {
    @Override
    public Class<? extends ReminderEvent> getClassByEventType(String type) {
        return EventType.valueOf(type).getType();
    }
}

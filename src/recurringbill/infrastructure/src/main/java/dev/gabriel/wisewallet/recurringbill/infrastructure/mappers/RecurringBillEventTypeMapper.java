package dev.gabriel.wisewallet.recurringbill.infrastructure.mappers;

import dev.gabriel.wisewallet.core.domain.models.mappers.EventTypeMapper;
import dev.gabriel.wisewallet.recurringbill.domain.events.EventType;
import dev.gabriel.wisewallet.recurringbill.domain.events.RecurringBillEvent;
import org.springframework.stereotype.Component;

@Component
public class RecurringBillEventTypeMapper implements EventTypeMapper {
    @Override
    public Class<? extends RecurringBillEvent> getClassByEventType(String type) {
        return EventType.valueOf(type).getType();
    }
}

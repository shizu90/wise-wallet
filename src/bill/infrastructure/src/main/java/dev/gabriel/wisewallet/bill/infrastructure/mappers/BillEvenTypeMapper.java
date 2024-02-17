package dev.gabriel.wisewallet.bill.infrastructure.mappers;

import dev.gabriel.wisewallet.bill.domain.events.BillEvent;
import dev.gabriel.wisewallet.bill.domain.events.EventType;
import dev.gabriel.wisewallet.core.domain.models.mappers.EventTypeMapper;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class BillEvenTypeMapper implements EventTypeMapper {
    @Override
    public Class<? extends BillEvent> getClassByEventType(String eventType) {
        return EventType.valueOf(eventType).getType();
    }
}

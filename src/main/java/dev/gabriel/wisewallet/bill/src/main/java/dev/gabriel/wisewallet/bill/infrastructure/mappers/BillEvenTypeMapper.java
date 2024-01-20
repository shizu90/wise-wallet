package dev.gabriel.wisewallet.bill.infrastructure.mappers;

import dev.gabriel.wisewallet.bill.domain.events.BillEvent;
import dev.gabriel.wisewallet.bill.domain.events.EventType;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers.EventTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class BillEvenTypeMapper implements EventTypeMapper {
    @Override
    public Class<? extends BillEvent> getClassByEventType(String eventType) {
        return EventType.valueOf(eventType).getType();
    }
}

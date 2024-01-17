package dev.gabriel.wisewallet.infrastructure.mappers;

import dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers.EventTypeMapper;
import dev.gabriel.wisewallet.domain.events.EventType;
import dev.gabriel.wisewallet.domain.events.WalletEvent;
import org.springframework.stereotype.Component;

@Component
public class WalletEventTypeMapper implements EventTypeMapper {
    @Override
    public Class<? extends WalletEvent> getClassByEventType(String eventType) {
        return EventType.valueOf(eventType).getType();
    }
}

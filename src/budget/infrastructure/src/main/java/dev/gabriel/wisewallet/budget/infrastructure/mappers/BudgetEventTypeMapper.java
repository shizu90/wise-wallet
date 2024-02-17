package dev.gabriel.wisewallet.budget.infrastructure.mappers;

import dev.gabriel.wisewallet.budget.domain.events.BudgetEvent;
import dev.gabriel.wisewallet.budget.domain.events.EventType;
import dev.gabriel.wisewallet.core.domain.models.mappers.EventTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class BudgetEventTypeMapper implements EventTypeMapper {
    @Override
    public Class<? extends BudgetEvent> getClassByEventType(String type) {
        return EventType.valueOf(type).getType();
    }
}

package dev.gabriel.wisewallet.reminder.infrastructure.mappers;

import dev.gabriel.wisewallet.core.domain.models.mappers.AggregateTypeMapper;
import dev.gabriel.wisewallet.reminder.domain.models.AggregateType;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import org.springframework.stereotype.Component;

@Component
public class ReminderAggregateTypeMapper implements AggregateTypeMapper {
    @Override
    public Class<Reminder> getClassByAggregateType(String type) {
        return AggregateType.valueOf(type).getType();
    }
}

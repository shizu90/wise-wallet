package dev.gabriel.wisewallet.recurringbill.infrastructure.mappers;

import dev.gabriel.wisewallet.core.domain.models.mappers.AggregateTypeMapper;
import dev.gabriel.wisewallet.recurringbill.domain.models.AggregateType;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import org.springframework.stereotype.Component;

@Component
public class RecurringBillAggregateTypeMapper implements AggregateTypeMapper {
    @Override
    public Class<RecurringBill> getClassByAggregateType(String type) {
        return AggregateType.valueOf(type).getType();
    }
}

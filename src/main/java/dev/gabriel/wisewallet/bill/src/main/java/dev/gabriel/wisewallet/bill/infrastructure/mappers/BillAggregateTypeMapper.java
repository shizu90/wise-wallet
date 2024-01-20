package dev.gabriel.wisewallet.bill.infrastructure.mappers;

import dev.gabriel.wisewallet.bill.domain.models.AggregateType;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers.AggregateTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class BillAggregateTypeMapper implements AggregateTypeMapper {
    @Override
    public Class<Bill> getClassByAggregateType(String aggregateType) {
        return AggregateType.BILL.getType();
    }
}

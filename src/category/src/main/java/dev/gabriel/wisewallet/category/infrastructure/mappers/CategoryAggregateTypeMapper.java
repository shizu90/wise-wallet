package dev.gabriel.wisewallet.category.infrastructure.mappers;

import dev.gabriel.wisewallet.category.domain.models.AggregateType;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers.AggregateTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryAggregateTypeMapper implements AggregateTypeMapper {

    @Override
    public Class<? extends Aggregate> getClassByAggregateType(String aggregateType) {
        return AggregateType.valueOf(aggregateType).getType();
    }
}

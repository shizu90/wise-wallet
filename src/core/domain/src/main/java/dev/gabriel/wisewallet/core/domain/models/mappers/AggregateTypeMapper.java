package dev.gabriel.wisewallet.core.domain.models.mappers;

import dev.gabriel.wisewallet.core.domain.models.Aggregate;

public interface AggregateTypeMapper {
    Class<? extends Aggregate> getClassByAggregateType(String aggregateType);
}

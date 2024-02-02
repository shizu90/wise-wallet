package dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers;

import dev.gabriel.wisewallet.core.domain.models.Aggregate;

public interface AggregateTypeMapper {
    Class<? extends Aggregate> getClassByAggregateType(String aggregateType);
}

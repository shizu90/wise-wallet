package dev.gabriel.wisewallet.core.domain.models;

import java.util.UUID;

public interface AggregateFactory {
    <T extends Aggregate> T create(String aggregateType, UUID aggregateId, Long version);
}

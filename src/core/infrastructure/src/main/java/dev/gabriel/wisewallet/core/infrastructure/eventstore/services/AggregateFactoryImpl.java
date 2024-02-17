package dev.gabriel.wisewallet.core.infrastructure.eventstore.services;

import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.core.domain.models.AggregateFactory;
import dev.gabriel.wisewallet.core.domain.models.mappers.AggregateTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AggregateFactoryImpl implements AggregateFactory {
    private final AggregateTypeMapper aggregateTypeMapper;

    @SneakyThrows(ReflectiveOperationException.class)
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Aggregate> T create(String aggregateType, UUID aggregateId, Long version) {
        Class<? extends Aggregate> aggregateClass = aggregateTypeMapper.getClassByAggregateType(aggregateType);
        Constructor<?> constructor = aggregateClass.getDeclaredConstructor(UUID.class, Long.class);
        constructor.setAccessible(true);
        return (T) constructor.newInstance(aggregateId, version);
    }
}

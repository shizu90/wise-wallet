package dev.gabriel.wisewallet.core.domain.models;

import dev.gabriel.wisewallet.core.domain.models.mappers.AggregateTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AggregateFactory {
    private final AggregateTypeMapper aggregateTypeMapper;

    @SneakyThrows(ReflectiveOperationException.class)
    @SuppressWarnings("unchecked")
    public <T extends Aggregate> T create(String aggregateType, UUID aggregateId, Long version) {
        Class<? extends Aggregate> aggregateClass = aggregateTypeMapper.getClassByAggregateType(aggregateType);
        Constructor<?> constructor = aggregateClass.getDeclaredConstructor(UUID.class, Long.class);
        constructor.setAccessible(true);
        return (T) constructor.newInstance(aggregateId, version);
    }
}

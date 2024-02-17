package dev.gabriel.wisewallet.user.infrastructure.mappers;

import dev.gabriel.wisewallet.core.domain.models.mappers.AggregateTypeMapper;
import dev.gabriel.wisewallet.user.domain.models.AggregateType;
import dev.gabriel.wisewallet.user.domain.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserAggregateTypeMapper implements AggregateTypeMapper {
    @Override
    public Class<User> getClassByAggregateType(String aggregateType) {
        return AggregateType.valueOf(aggregateType).getType();
    }
}

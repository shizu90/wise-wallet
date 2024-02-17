package dev.gabriel.wisewallet.budget.infrastructure.mappers;

import dev.gabriel.wisewallet.budget.domain.models.AggregateType;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.core.domain.models.mappers.AggregateTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class BudgetAggregateTypeMapper implements AggregateTypeMapper {
    @Override
    public Class<Budget> getClassByAggregateType(String type) {
        return AggregateType.valueOf(type).getType();
    }
}

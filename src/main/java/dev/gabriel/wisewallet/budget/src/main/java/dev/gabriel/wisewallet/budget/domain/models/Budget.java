package dev.gabriel.wisewallet.budget.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;

import java.util.UUID;

public class Budget extends Aggregate {

    @JsonCreator
    private Budget(UUID id, Long version) {
        super(id, version);
    }

    @Override
    public String getAggregateType() {
        return AggregateType.BUDGET.toString();
    }
}

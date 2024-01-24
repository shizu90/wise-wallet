package dev.gabriel.wisewallet.budget.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AggregateType {
    BUDGET(Budget.class);

    private final Class<Budget> type;
}

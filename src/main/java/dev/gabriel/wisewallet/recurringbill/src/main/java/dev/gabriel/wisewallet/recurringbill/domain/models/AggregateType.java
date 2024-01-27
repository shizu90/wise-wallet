package dev.gabriel.wisewallet.recurringbill.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AggregateType {
    RECURRING_BILL(RecurringBill.class);

    private final Class<RecurringBill> type;
}

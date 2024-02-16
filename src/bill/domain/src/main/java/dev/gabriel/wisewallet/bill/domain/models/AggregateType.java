package dev.gabriel.wisewallet.bill.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AggregateType {
    BILL(Bill.class);

    private final Class<Bill> type;
}

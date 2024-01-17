package dev.gabriel.wisewallet.domain.models;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AggregateType {
    WALLET(Wallet.class);

    private final Class<Wallet> type;
}

package dev.gabriel.wisewallet.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AggregateType {
    WALLET(Wallet.class);

    private final Class<Wallet> type;
}

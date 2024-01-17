package dev.gabriel.wisewallet.domain.events;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum EventType {
    WALLET_CREATED(WalletCreatedEvent.class),
    WALLET_RENAMED(WalletRenamedEvent.class),
    WALLET_DESCRIPTION_CHANGED(WalletDescriptionChangedEvent.class),
    WALLET_BALANCE_UPDATED(WalletBalanceUpdatedEvent.class),
    WALLET_CURRENCY_CODE_CHANGED(WalletCurrencyCodeChangedEvent.class),
    WALLET_TYPE_CHANGED(WalletTypeChangedEvent.class),
    WALLET_MAIN_TOGGLED(WalletMainToggledEvent.class),
    WALLET_DELETED(WalletDeletedEvent.class);

    private final Class<? extends WalletEvent> event;
}

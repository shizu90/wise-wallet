package dev.gabriel.wisewallet.bill.domain.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EventType {
    BILL_CREATED(BillCreatedEvent.class),
    BILL_RENAMED(BillRenamedEvent.class),
    BILL_DESCRIPTION_CHANGED(BillDescriptionChangedEvent.class),
    BILL_TYPE_CHANGED(BillTypeChangedEvent.class),
    BILL_AMOUNT_UPDATED(BillAmountUpdatedEvent.class),
    BILL_CURRENCY_CODE_CHANGED(BillCurrencyCodeChangedEvent.class),
    BILL_WALLET_CHANGED(BillWalletChangedEvent.class),
    BILL_CATEGORY_CHANGED(BillCategoryChangedEvent.class),
    BILL_DELETED(BillDeletedEvent.class);

    private final Class<? extends BillEvent> type;
}

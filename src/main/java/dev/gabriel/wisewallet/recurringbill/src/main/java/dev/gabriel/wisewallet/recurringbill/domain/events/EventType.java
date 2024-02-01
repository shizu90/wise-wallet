package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EventType {
    RECURRING_BILL_CREATED(RecurringBillCreatedEvent.class),
    RECURRING_BILL_RENAMED(RecurringBillRenamedEvent.class),
    RECURRING_BILL_DESCRIPTION_CHANGED(RecurringBillDescriptionChangedEvent.class),
    RECURRING_BILL_AMOUNT_UPDATED(RecurringBillAmountUpdatedEvent.class),
    RECURRING_BILL_CURRENCY_CODE_CHANGED(RecurringBillCurrencyCodeChangedEvent.class),
    RECURRING_BILL_RECURRENCE_CHANGED(RecurringBillRecurrenceChangedEvent.class),
    RECURRING_BILL_TYPE_CHANGED(RecurringBillTypeChangedEvent.class),
    RECURRING_BILL_MAX_PERIODS_CHANGED(RecurringBillMaxPeriodsChangedEvent.class),
    RECURRING_BILL_WALLET_CHANGED(RecurringBillWalletChangedEvent.class),
    RECURRING_BILL_CATEGORY_CHANGED(RecurringBillCategoryChangedEvent.class),
    RECURRING_BILL_PERIOD_EXECUTED(RecurringBillPeriodExecutedEvent.class),
    RECURRING_BILL_RESET(RecurringBillResetEvent.class),
    RECURRING_BILL_DELETED(RecurringBillDeletedEvent.class);

    private final Class<? extends RecurringBillEvent> type;
}

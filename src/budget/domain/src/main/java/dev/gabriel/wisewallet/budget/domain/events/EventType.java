package dev.gabriel.wisewallet.budget.domain.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EventType {
    BUDGET_CREATED(BudgetCreatedEvent.class),
    BUDGET_RENAMED(BudgetRenamedEvent.class),
    BUDGET_DESCRIPTION_CHANGED(BudgetDescriptionChangedEvent.class),
    BUDGET_AMOUNT_UPDATED(BudgetAmountUpdatedEvent.class),
    BUDGET_CURRENCY_CODE_CHANGED(BudgetCurrencyCodeChangedEvent.class),
    BUDGET_ITEM_ADDED(BudgetItemAddedEvent.class),
    BUDGET_ITEM_REMOVED(BudgetItemRemovedEvent.class),
    BUDGET_DELETED(BudgetDeletedEvent.class);

    private final Class<? extends BudgetEvent> type;
}

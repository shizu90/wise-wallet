package dev.gabriel.wisewallet.budget.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BudgetCurrencyCodeChangedEvent extends BudgetEvent {
    private final String currencyCode;

    public BudgetCurrencyCodeChangedEvent(UUID id, Long version, String currencyCode) {
        super(id, version);
        this.currencyCode = currencyCode;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_CURRENCY_CODE_CHANGED.toString();
    }
}

package dev.gabriel.wisewallet.budget.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class BudgetItemAddedEvent extends BudgetEvent {
    private final UUID billId;
    private final String name;
    private final BigDecimal amount;
    private final String currencyCode;
    private final String type;

    public BudgetItemAddedEvent(UUID id, Long version, UUID billId, String name, BigDecimal amount, String currencyCode, String type) {
        super(id, version);
        this.billId = billId;
        this.name = name;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.type = type;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_ITEM_ADDED.toString();
    }
}

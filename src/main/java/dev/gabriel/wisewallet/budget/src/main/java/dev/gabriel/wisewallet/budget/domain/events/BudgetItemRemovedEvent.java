package dev.gabriel.wisewallet.budget.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class BudgetItemRemovedEvent extends BudgetEvent {
    private final UUID billId;
    private final BigDecimal amount;

    public BudgetItemRemovedEvent(UUID id, Long version, UUID billId, BigDecimal amount) {
        super(id, version);
        this.billId = billId;
        this.amount = amount;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_ITEM_REMOVED.toString();
    }
}

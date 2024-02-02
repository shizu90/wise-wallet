package dev.gabriel.wisewallet.budget.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BudgetItemRemovedEvent extends BudgetEvent {
    private final UUID billId;

    public BudgetItemRemovedEvent(UUID id, Long version, UUID billId) {
        super(id, version);
        this.billId = billId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_ITEM_REMOVED.toString();
    }
}

package dev.gabriel.wisewallet.budget.domain.events;

import lombok.NonNull;

import java.util.UUID;

public class BudgetDeletedEvent extends BudgetEvent {
    public BudgetDeletedEvent(UUID id, Long version) {
        super(id, version);
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_DELETED.toString();
    }
}

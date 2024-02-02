package dev.gabriel.wisewallet.budget.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BudgetRenamedEvent extends BudgetEvent {
    private final String name;

    public BudgetRenamedEvent(UUID id, Long version, String name) {
        super(id, version);
        this.name = name;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_RENAMED.toString();
    }
}

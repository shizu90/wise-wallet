package dev.gabriel.wisewallet.budget.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BudgetDescriptionChangedEvent extends BudgetEvent {
    private final String description;

    public BudgetDescriptionChangedEvent(UUID id, Long version, String description) {
        super(id, version);
        this.description = description;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_DESCRIPTION_CHANGED.toString();
    }
}

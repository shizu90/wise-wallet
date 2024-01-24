package dev.gabriel.wisewallet.budget.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BudgetCreatedEvent extends BudgetEvent {
    private final String name;
    private final String description;
    private final String currencyCode;
    private final UUID userId;

    public BudgetCreatedEvent(UUID id,
                              Long version,
                              String name,
                              String description,
                              String currencyCode,
                              UUID userId
    ) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.currencyCode = currencyCode;
        this.userId = userId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_CREATED.toString();
    }
}

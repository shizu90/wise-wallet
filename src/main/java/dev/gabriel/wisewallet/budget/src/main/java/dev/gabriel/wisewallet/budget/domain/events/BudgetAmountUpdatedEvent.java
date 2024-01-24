package dev.gabriel.wisewallet.budget.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class BudgetAmountUpdatedEvent extends BudgetEvent {
    private final BigDecimal amount;

    public BudgetAmountUpdatedEvent(UUID id, Long version, BigDecimal amount) {
        super(id, version);
        this.amount = amount;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BUDGET_AMOUNT_UPDATED.toString();
    }
}

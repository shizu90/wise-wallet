package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillCategoryChangedEvent extends RecurringBillEvent {
    private final UUID categoryId;

    public RecurringBillCategoryChangedEvent(UUID id, Long version, UUID categoryId) {
        super(id, version);
        this.categoryId = categoryId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_CATEGORY_CHANGED.toString();
    }
}

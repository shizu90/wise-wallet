package dev.gabriel.wisewallet.bill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BillCategoryChangedEvent extends BillEvent {
    private final UUID categoryId;

    public BillCategoryChangedEvent(UUID id, Long version, UUID categoryId) {
        super(id, version);
        this.categoryId = categoryId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BILL_CATEGORY_CHANGED.toString();
    }
}

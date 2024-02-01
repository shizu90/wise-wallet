package dev.gabriel.wisewallet.recurringbill.domain.events;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBillType;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillTypeChangedEvent extends RecurringBillEvent {
    private final RecurringBillType type;

    public RecurringBillTypeChangedEvent(UUID id, Long version, RecurringBillType type) {
        super(id, version);
        this.type = type;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_TYPE_CHANGED.toString();
    }
}

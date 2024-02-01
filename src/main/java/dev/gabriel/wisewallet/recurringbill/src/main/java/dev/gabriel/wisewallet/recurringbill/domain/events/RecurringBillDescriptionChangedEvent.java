package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillDescriptionChangedEvent extends RecurringBillEvent {
    private final String description;

    public RecurringBillDescriptionChangedEvent(UUID id, Long version, String description) {
        super(id, version);
        this.description = description;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_DESCRIPTION_CHANGED.toString();
    }
}
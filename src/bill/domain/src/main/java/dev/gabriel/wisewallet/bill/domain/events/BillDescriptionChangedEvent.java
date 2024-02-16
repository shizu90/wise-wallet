package dev.gabriel.wisewallet.bill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BillDescriptionChangedEvent extends BillEvent {
    private final String description;

    public BillDescriptionChangedEvent(UUID aggregateId, Long version, String description) {
        super(aggregateId, version);
        this.description = description;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BILL_DESCRIPTION_CHANGED.toString();
    }
}

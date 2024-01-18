package dev.gabriel.wisewallet.bill.domain.events;

import dev.gabriel.wisewallet.bill.domain.models.BillType;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BillTypeChangedEvent extends BillEvent {
    private final BillType type;

    public BillTypeChangedEvent(UUID aggregateId, Long version, BillType type) {
        super(aggregateId, version);
        this.type = type;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BILL_TYPE_CHANGED.toString();
    }
}

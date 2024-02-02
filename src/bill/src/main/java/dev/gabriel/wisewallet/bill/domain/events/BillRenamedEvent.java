package dev.gabriel.wisewallet.bill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class BillRenamedEvent extends BillEvent {
    private final String name;

    public BillRenamedEvent(UUID aggregateId, Long version, String name) {
        super(aggregateId, version);
        this.name = name;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BILL_RENAMED.toString();
    }
}

package dev.gabriel.wisewallet.recurringbill.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class RecurringBillRenamedEvent extends RecurringBillEvent {
    private final String name;

    public RecurringBillRenamedEvent(UUID id, Long version, String name) {
        super(id, version);
        this.name = name;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_RENAMED.toString();
    }
}

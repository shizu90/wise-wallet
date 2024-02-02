package dev.gabriel.wisewallet.recurringbill.domain.events;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBillType;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class RecurringBillCreatedEvent extends RecurringBillEvent {
    private final String name;
    private final String description;
    private final BigDecimal amount;
    private final String currencyCode;
    private final Long recurrence;
    private final RecurringBillType type;
    private final Long maxPeriods;
    private final UUID walletId;
    private final UUID categoryId;

    public RecurringBillCreatedEvent(UUID id,
                                     Long version,
                                     String name,
                                     String description,
                                     BigDecimal amount,
                                     String currencyCode,
                                     Long recurrence,
                                     RecurringBillType type,
                                     Long maxPeriods,
                                     UUID walletId,
                                     UUID categoryId
    ) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.recurrence = recurrence;
        this.type = type;
        this.maxPeriods = maxPeriods;
        this.walletId = walletId;
        this.categoryId = categoryId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.RECURRING_BILL_CREATED.toString();
    }
}

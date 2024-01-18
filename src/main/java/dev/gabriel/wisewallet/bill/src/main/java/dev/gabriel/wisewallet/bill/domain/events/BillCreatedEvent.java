package dev.gabriel.wisewallet.bill.domain.events;

import dev.gabriel.wisewallet.bill.domain.models.BillType;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class BillCreatedEvent extends BillEvent {
    private final String name;
    private final String description;
    private final BigDecimal amount;
    private final String currencyCode;
    private final BillType type;
    private final UUID walletId;
    private final UUID categoryId;

    public BillCreatedEvent(UUID aggregateId,
                            Long version,
                            String name,
                            String description,
                            BigDecimal amount,
                            String currencyCode,
                            BillType type,
                            UUID walletId,
                            UUID categoryId
    ) {
        super(aggregateId, version);
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.type = type;
        this.walletId = walletId;
        this.categoryId = categoryId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.BILL_CREATED.toString();
    }
}

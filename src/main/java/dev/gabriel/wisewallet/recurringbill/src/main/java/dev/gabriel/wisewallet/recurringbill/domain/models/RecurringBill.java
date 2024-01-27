package dev.gabriel.wisewallet.recurringbill.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Currency;
import java.util.UUID;

@Getter
public class RecurringBill extends Aggregate {
    private RecurringBillName name;
    private RecurringBillDescription description;
    private Currency amount;
    private RecurringBillRecurrence recurrence;
    private RecurringBillPeriod maxPeriods;
    private RecurringBillPeriod currentPeriods;
    private LocalDate lastPeriod;
    private UUID walletId;
    private UUID categoryId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    @JsonCreator
    private RecurringBill(UUID id, Long version) {
        super(id, version);
    }

    @Override
    public String getAggregateType() {
        return AggregateType.RECURRING_BILL.toString();
    }
}

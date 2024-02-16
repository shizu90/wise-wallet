package dev.gabriel.wisewallet.recurringbill.infrastructure.services;

import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.recurringbill.application.events.RecurringBillSyncEventHandler;
import dev.gabriel.wisewallet.recurringbill.domain.models.AggregateType;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjection;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjectionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecurringBillSyncEventHandlerImpl implements RecurringBillSyncEventHandler {
    private final RecurringBillProjectionRepository projectionRepository;

    @Override
    public void handleEvents(Aggregate aggregate) {
        RecurringBill recurringBill = (RecurringBill) aggregate;
        RecurringBillProjection recurringBillProjection = RecurringBillProjection.create(
                recurringBill.getId(),
                recurringBill.getDescription().getValue(),
                recurringBill.getName().getValue(),
                recurringBill.getType(),
                recurringBill.getAmount().getValue(),
                recurringBill.getAmount().getCurrencyCode(),
                recurringBill.getRecurrence().getValue(),
                recurringBill.getCurrentPeriods().getValue(),
                recurringBill.getMaxPeriods().getValue(),
                recurringBill.getLastPeriod(),
                recurringBill.getWalletId(),
                recurringBill.getCategoryId(),
                recurringBill.getCreatedAt(),
                recurringBill.getUpdatedAt(),
                recurringBill.getIsDeleted()
        );

        projectionRepository.save(recurringBillProjection);
    }

    @Override
    @NonNull
    public String getAggregateType() {
        return AggregateType.RECURRING_BILL.toString();
    }
}

package dev.gabriel.wisewallet.recurringbill.infrastructure.eventstore;

import dev.gabriel.wisewallet.core.infrastructure.eventstore.services.AggregateService;
import dev.gabriel.wisewallet.recurringbill.domain.models.AggregateType;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;
import dev.gabriel.wisewallet.recurringbill.domain.repositories.RecurringBillRepository;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecurringBillEventStore implements RecurringBillRepository {
    private final AggregateService aggregateService;

    @Override
    public Optional<RecurringBill> load(@NonNull UUID id, @Nullable Long version) {
        RecurringBill recurringBill = (RecurringBill) aggregateService.load(AggregateType.RECURRING_BILL.toString(), id, version);
        return Optional.ofNullable(recurringBill);
    }

    @Override
    public void saveChanges(@NonNull RecurringBill recurringBill) {
        aggregateService.save(recurringBill);
    }
}

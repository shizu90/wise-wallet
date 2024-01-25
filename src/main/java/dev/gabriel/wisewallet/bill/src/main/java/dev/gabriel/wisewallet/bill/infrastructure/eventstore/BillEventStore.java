package dev.gabriel.wisewallet.bill.infrastructure.eventstore;

import dev.gabriel.wisewallet.bill.domain.models.AggregateType;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.services.AggregateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillEventStore implements BillRepository {
    private final AggregateService aggregateService;

    @Override
    public Optional<Bill> load(UUID id) {
        Bill bill = (Bill) aggregateService.load(AggregateType.BILL.toString(), id, null);
        return Optional.ofNullable(bill);
    }

    @Override
    public void saveChanges(Bill bill) {
        aggregateService.save(bill);
    }
}

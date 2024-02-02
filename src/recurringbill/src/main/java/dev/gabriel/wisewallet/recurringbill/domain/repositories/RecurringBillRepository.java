package dev.gabriel.wisewallet.recurringbill.domain.repositories;

import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBill;

import java.util.Optional;
import java.util.UUID;

public interface RecurringBillRepository extends DomainRepository {
    Optional<RecurringBill> load(UUID id);
    void saveChanges(RecurringBill recurringBill);
}

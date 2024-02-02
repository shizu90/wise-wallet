package dev.gabriel.wisewallet.bill.domain.repositories;

import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;

import java.util.Optional;
import java.util.UUID;

public interface BillRepository extends DomainRepository {
    Optional<Bill> load(UUID id);
    void saveChanges(Bill bill);
}

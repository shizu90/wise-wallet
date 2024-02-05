package dev.gabriel.wisewallet.bill.domain.repositories;

import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import jakarta.annotation.Nullable;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface BillRepository extends DomainRepository {
    Optional<Bill> load(@NonNull UUID id, @Nullable Long version);
    void saveChanges(@NonNull Bill bill);
}

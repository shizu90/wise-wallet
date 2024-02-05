package dev.gabriel.wisewallet.recurringbill.infrastructure.services;

import dev.gabriel.wisewallet.recurringbill.domain.services.CheckUniqueRecurringBillName;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckUniqueRecurringBillNameService implements CheckUniqueRecurringBillName {
    private final RecurringBillProjectionRepository recurringBillProjectionRepository;

    @Override
    public boolean exists(String name, UUID walletId) {
        return !recurringBillProjectionRepository.findByNameAndWalletId(name, walletId).isEmpty();
    }
}

package dev.gabriel.wisewallet.bill.infrastructure.services;

import dev.gabriel.wisewallet.bill.domain.services.CheckUniqueBillName;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckUniqueBillNameService implements CheckUniqueBillName {
    private final BillProjectionRepository billProjectionRepository;

    @Override
    public boolean exists(String name, UUID walletId) {
        return !billProjectionRepository.findByNameAndWalletId(name, walletId).isEmpty();
    }
}

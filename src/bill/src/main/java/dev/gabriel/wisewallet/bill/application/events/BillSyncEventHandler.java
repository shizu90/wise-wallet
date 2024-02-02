package dev.gabriel.wisewallet.bill.application.events;

import dev.gabriel.wisewallet.bill.domain.models.AggregateType;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjection;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjectionRepository;
import dev.gabriel.wisewallet.core.application.SyncEventHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillSyncEventHandler implements SyncEventHandler {
    private final BillProjectionRepository projectionRepository;

    @Override
    public void handleEvents(Aggregate aggregate) {
        Bill bill = (Bill) aggregate;
        BillProjection billProjection = BillProjection.create(
                bill.getId(),
                bill.getName().getValue(),
                bill.getDescription().getValue(),
                bill.getAmount().getValue(),
                bill.getAmount().getCurrencyCode(),
                bill.getType(),
                bill.getWalletId(),
                bill.getCategoryId(),
                bill.getCreatedAt(),
                bill.getUpdatedAt(),
                bill.getIsDeleted()
        );

        projectionRepository.save(billProjection);
    }

    @Override
    @NonNull
    public String getAggregateType() {
        return AggregateType.BILL.toString();
    }
}

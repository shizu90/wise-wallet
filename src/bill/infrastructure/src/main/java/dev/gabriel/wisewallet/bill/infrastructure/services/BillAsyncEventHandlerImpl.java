package dev.gabriel.wisewallet.bill.infrastructure.services;

import dev.gabriel.wisewallet.bill.application.events.BillAsyncEventHandler;
import dev.gabriel.wisewallet.bill.domain.commands.BillCommand;
import dev.gabriel.wisewallet.bill.domain.commands.ChangeBillCategoryCommand;
import dev.gabriel.wisewallet.bill.domain.commands.DeleteBillCommand;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjection;
import dev.gabriel.wisewallet.bill.infrastructure.projection.BillProjectionRepository;
import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.wallet.domain.events.WalletDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillAsyncEventHandlerImpl implements BillAsyncEventHandler {
    private final BillProjectionRepository billProjectionRepository;
    private final CommandBus<BillCommand> commandBus;

    @Override
    public void handle(WalletDeletedEvent event) {
        List<BillProjection> bills = billProjectionRepository.findByWalletId(event.getAggregateId());

        for(BillProjection bill : bills) {
            commandBus.execute(new DeleteBillCommand(bill.getId()));
        }
    }

    @Override
    public void handle(CategoryDeletedEvent event) {
        List<BillProjection> bills = billProjectionRepository.findByCategoryId(event.getAggregateId());

        for(BillProjection bill : bills) {
            commandBus.execute(new ChangeBillCategoryCommand(bill.getId(), null));
        }
    }
}

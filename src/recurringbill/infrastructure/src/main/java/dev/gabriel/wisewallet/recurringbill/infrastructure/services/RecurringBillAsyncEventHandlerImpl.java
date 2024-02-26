package dev.gabriel.wisewallet.recurringbill.infrastructure.services;

import dev.gabriel.wisewallet.category.domain.events.CategoryDeletedEvent;
import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.recurringbill.application.events.RecurringBillAsyncEventHandler;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ChangeRecurringBillCategoryCommand;
import dev.gabriel.wisewallet.recurringbill.domain.commands.DeleteRecurringBillCommand;
import dev.gabriel.wisewallet.recurringbill.domain.commands.RecurringBillCommand;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjection;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjectionRepository;
import dev.gabriel.wisewallet.wallet.domain.events.WalletDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecurringBillAsyncEventHandlerImpl implements RecurringBillAsyncEventHandler {
    private final RecurringBillProjectionRepository recurringBillProjectionRepository;
    private final CommandBus<RecurringBillCommand> commandBus;

    @Override
    public void handle(CategoryDeletedEvent event) {
        List<RecurringBillProjection> recurringBills = recurringBillProjectionRepository.findByCategoryId(event.getAggregateId());

        for(RecurringBillProjection recurringBill : recurringBills) {
            commandBus.execute(new ChangeRecurringBillCategoryCommand(recurringBill.getId(), null));
        }
    }

    @Override
    public void handle(WalletDeletedEvent event) {
        List<RecurringBillProjection> recurringBills = recurringBillProjectionRepository.findByWalletId(event.getAggregateId());

        for(RecurringBillProjection recurringBill : recurringBills) {
            commandBus.execute(new DeleteRecurringBillCommand(recurringBill.getId()));
        }
    }
}

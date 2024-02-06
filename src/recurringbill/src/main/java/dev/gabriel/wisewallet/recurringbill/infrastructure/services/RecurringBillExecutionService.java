package dev.gabriel.wisewallet.recurringbill.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.recurringbill.domain.commands.ExecuteRecurringBillPeriodCommand;
import dev.gabriel.wisewallet.recurringbill.domain.commands.RecurringBillCommand;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjection;
import dev.gabriel.wisewallet.recurringbill.infrastructure.projection.RecurringBillProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecurringBillExecutionService {
    private final RecurringBillProjectionRepository recurringBillProjectionRepository;
    private final CommandBus<RecurringBillCommand> commandBus;

    @Async
    @Scheduled(cron = "0 0 0 * * *")
    public void execute() {
        List<RecurringBillProjection> recurringBills = recurringBillProjectionRepository
                .findAll()
                .stream()
                .filter(recurringBill -> recurringBill.getCurrentPeriods() < recurringBill.getMaxPeriods())
                .toList();

        for(RecurringBillProjection recurringBill : recurringBills) {
            commandBus.execute(new ExecuteRecurringBillPeriodCommand(recurringBill.getId(), 1L));
        }
    }
}

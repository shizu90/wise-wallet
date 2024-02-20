package dev.gabriel.wisewallet.budget.infrastructure.services;

import dev.gabriel.wisewallet.bill.domain.events.*;
import dev.gabriel.wisewallet.budget.application.events.BudgetAsyncEventHandler;
import dev.gabriel.wisewallet.budget.domain.commands.AddBudgetItemCommand;
import dev.gabriel.wisewallet.budget.domain.commands.BudgetCommand;
import dev.gabriel.wisewallet.budget.domain.commands.RemoveBudgetItemCommand;
import dev.gabriel.wisewallet.budget.domain.models.BudgetItem;
import dev.gabriel.wisewallet.budget.infrastructure.projection.BudgetProjection;
import dev.gabriel.wisewallet.budget.infrastructure.projection.BudgetProjectionRepository;
import dev.gabriel.wisewallet.core.application.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetEventConsumer implements BudgetAsyncEventHandler {
    private final BudgetProjectionRepository budgetProjectionRepository;
    private final CommandBus<BudgetCommand> commandBus;
    private static final String GROUP_ID = "budget-consumer";

    @KafkaListener(topics = "BillAmountUpdatedEvent", groupId = GROUP_ID)
    @Override
    public void handle(BillAmountUpdatedEvent event) {
        List<BudgetProjection> budgets = budgetProjectionRepository.findByBillId(event.getAggregateId());

        for(BudgetProjection budget : budgets) {
            BudgetItem item = budget.getItems()
                    .stream()
                    .filter(i -> i.getBillId().equals(event.getAggregateId()))
                    .findFirst()
                    .orElse(null);
            if(item != null) {
                commandBus.execute(new RemoveBudgetItemCommand(budget.getId(), event.getAggregateId()));
                commandBus.execute(new AddBudgetItemCommand(
                        budget.getId(),
                        event.getAggregateId(),
                        item.getName(),
                        event.getAmount(),
                        item.getCurrencyCode(),
                        item.getType()
                ));
            }
        }
    }

    @KafkaListener(topics = "BillCurrencyCodeChangedEvent", groupId = GROUP_ID)
    @Override
    public void handle(BillCurrencyCodeChangedEvent event) {
        List<BudgetProjection> budgets = budgetProjectionRepository.findByBillId(event.getAggregateId());

        for(BudgetProjection budget : budgets) {
            BudgetItem item = budget.getItems()
                    .stream()
                    .filter(i -> i.getBillId().equals(event.getAggregateId()))
                    .findFirst()
                    .orElse(null);

            if(item != null) {
                commandBus.execute(new RemoveBudgetItemCommand(budget.getId(), event.getAggregateId()));
                commandBus.execute(new AddBudgetItemCommand(
                        budget.getId(),
                        event.getAggregateId(),
                        item.getName(),
                        item.getAmount(),
                        event.getCurrencyCode(),
                        item.getType()
                ));
            }
        }
    }

    @KafkaListener(topics = "BillTypeChangedEvent", groupId = GROUP_ID)
    @Override
    public void handle(BillTypeChangedEvent event) {
        List<BudgetProjection> budgets = budgetProjectionRepository.findByBillId(event.getAggregateId());

        for(BudgetProjection budget : budgets) {
            BudgetItem item = budget.getItems()
                    .stream()
                    .filter(i -> i.getBillId().equals(event.getAggregateId()))
                    .findFirst()
                    .orElse(null);

            if(item != null) {
                commandBus.execute(new RemoveBudgetItemCommand(budget.getId(), event.getAggregateId()));
                commandBus.execute(new AddBudgetItemCommand(
                        budget.getId(),
                        event.getAggregateId(),
                        item.getName(),
                        item.getAmount(),
                        item.getCurrencyCode(),
                        event.getType().toString()
                ));
            }
        }
    }

    @KafkaListener(topics = "BillRenamedEvent", groupId = GROUP_ID)
    @Override
    public void handle(BillRenamedEvent event) {
        List<BudgetProjection> budgets = budgetProjectionRepository.findByBillId(event.getAggregateId());

        for(BudgetProjection budget : budgets) {
            BudgetItem item = budget.getItems()
                    .stream()
                    .filter(i -> i.getBillId().equals(event.getAggregateId()))
                    .findFirst()
                    .orElse(null);

            if(item != null) {
                commandBus.execute(new RemoveBudgetItemCommand(budget.getId(), event.getAggregateId()));
                commandBus.execute(new AddBudgetItemCommand(
                        budget.getId(),
                        event.getAggregateId(),
                        event.getName(),
                        item.getAmount(),
                        item.getCurrencyCode(),
                        item.getType()
                ));
            }
        }
    }

    @KafkaListener(topics = "BillDeletedEvent", groupId = GROUP_ID)
    @Override
    public void handle(BillDeletedEvent event) {
        List<BudgetProjection> budgets = budgetProjectionRepository.findByBillId(event.getAggregateId());

        for(BudgetProjection budget : budgets) {
            budget.getItems()
                    .stream()
                    .filter(i -> i.getBillId().equals(event.getAggregateId()))
                    .findFirst()
                    .ifPresent(item ->
                            commandBus.execute(new RemoveBudgetItemCommand(budget.getId(), event.getAggregateId()))
                    );
        }
    }
}

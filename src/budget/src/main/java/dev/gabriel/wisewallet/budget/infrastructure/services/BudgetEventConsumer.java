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
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BudgetEventConsumer implements BudgetAsyncEventHandler {
    private final BudgetProjectionRepository budgetProjectionRepository;
    private final CommandBus<BudgetCommand> commandBus;

    @Override
    @KafkaListener(topics = "BillAmountUpdatedEvent")
    public void handle(BillAmountUpdatedEvent event, Acknowledgment ack) {
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

        ack.acknowledge();
    }

    @Override
    @KafkaListener(topics = "BillCurrencyCodeChangedEvent")
    public void handle(BillCurrencyCodeChangedEvent event, Acknowledgment ack) {
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

        ack.acknowledge();
    }

    @Override
    @KafkaListener(topics = "BillTypeChangedEvent")
    public void handle(BillTypeChangedEvent event, Acknowledgment ack) {
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

        ack.acknowledge();
    }

    @Override
    @KafkaListener(topics = "BillRenamedEvent")
    public void handle(BillRenamedEvent event, Acknowledgment ack) {
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

        ack.acknowledge();
    }

    @Override
    @KafkaListener(topics = "BillDeletedEvent")
    public void handle(BillDeletedEvent event, Acknowledgment ack) {
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

        ack.acknowledge();
    }
}

package dev.gabriel.wisewallet.budget.application.events;

import dev.gabriel.wisewallet.bill.domain.events.*;

public interface BudgetAsyncEventHandler {
    void handle(BillAmountUpdatedEvent event);
    void handle(BillTypeChangedEvent event);
    void handle(BillDeletedEvent event);
    void handle(BillCurrencyCodeChangedEvent event);
    void handle(BillRenamedEvent event);
}

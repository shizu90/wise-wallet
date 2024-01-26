package dev.gabriel.wisewallet.budget.domain.models;

import dev.gabriel.wisewallet.budget.domain.events.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetTests {
    @Test
    @DisplayName("Should create budget successfully.")
    void createBudget() {
        Budget budget = Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );

        Assertions.assertEquals(1L, budget.getVersion());
        Assertions.assertInstanceOf(BudgetCreatedEvent.class, budget.getChanges().get(0));
    }

    @Test
    @DisplayName("Should rename budget successfully.")
    void renameBudget() {
        Budget budget = Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );
        budget.rename("NewName");

        Assertions.assertEquals(2L, budget.getVersion());
        Assertions.assertInstanceOf(BudgetRenamedEvent.class, budget.getChanges().get(1));
        Assertions.assertEquals("NewName", budget.getName().getValue());
    }

    @Test
    @DisplayName("Should change budget description successfully.")
    void changeDescription() {
        Budget budget = Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );
        budget.changeDescription("NewDescription");

        Assertions.assertEquals(2L, budget.getVersion());
        Assertions.assertInstanceOf(BudgetDescriptionChangedEvent.class, budget.getChanges().get(1));
        Assertions.assertEquals("NewDescription", budget.getDescription().getValue());
    }

    @Test
    @DisplayName("Should change budget currency code successfully.")
    void changeCurrencyCode() {
        Budget budget = Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );
         budget.changeCurrencyCode("EUR");

         Assertions.assertEquals(2L, budget.getVersion());
         Assertions.assertInstanceOf(BudgetCurrencyCodeChangedEvent.class, budget.getChanges().get(1));
         Assertions.assertEquals("EUR", budget.getAmount().getCurrencyCode());
    }

    @Test
    @DisplayName("Should update budget amount successfully.")
    void updateAmount() {
        Budget budget = Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );
        budget.updateAmount(BigDecimal.valueOf(500.0));

        Assertions.assertEquals(2L, budget.getVersion());
        Assertions.assertInstanceOf(BudgetAmountUpdatedEvent.class, budget.getChanges().get(1));
        Assertions.assertEquals(BigDecimal.valueOf(500.0), budget.getAmount().getValue());
    }

    @Test
    @DisplayName("Should add budget item successfully.")
    void addItem() {
        Budget budget = Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );
        budget.addItem(
                UUID.randomUUID(),
                "Name",
                BigDecimal.valueOf(20.0),
                "BRL",
                "EXPENSE"
        );

        Assertions.assertEquals(2L, budget.getVersion());
        Assertions.assertInstanceOf(BudgetItemAddedEvent.class, budget.getChanges().get(1));
        Assertions.assertEquals(1, budget.getItems().size());
    }

    @Test
    @DisplayName("Should remove budget item successfully.")
    void removeItem() {
        Budget budget = Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );
        UUID billId = UUID.randomUUID();
        budget.addItem(
                billId,
                "Name",
                BigDecimal.valueOf(20.0),
                "BRL",
                "EXPENSE"
        );
        budget.removeItem(billId);

        Assertions.assertEquals(3L, budget.getVersion());
        Assertions.assertInstanceOf(BudgetItemRemovedEvent.class, budget.getChanges().get(2));
        Assertions.assertEquals(0, budget.getItems().size());
    }

    @Test
    @DisplayName("Should delete budget successfully.")
    void deleteBudget() {
        Budget budget = Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );
        budget.delete();

        Assertions.assertEquals(2L, budget.getVersion());
        Assertions.assertInstanceOf(BudgetDeletedEvent.class, budget.getChanges().get(1));
        Assertions.assertTrue(budget.getIsDeleted());
    }
}

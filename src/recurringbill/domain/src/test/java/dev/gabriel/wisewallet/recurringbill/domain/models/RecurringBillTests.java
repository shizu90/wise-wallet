package dev.gabriel.wisewallet.recurringbill.domain.models;

import dev.gabriel.wisewallet.recurringbill.domain.events.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class RecurringBillTests {
    private RecurringBill populate() {
        return RecurringBill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(20.0),
                "BRL",
                2L,
                RecurringBillType.EXPENSE,
                40L,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
    }

    @Test
    @DisplayName(value = "Should create recurring bill successfully.")
    void createRecurringBill() {
        RecurringBill recurringBill = populate();

        Assertions.assertEquals(1L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillCreatedEvent.class, recurringBill.getChanges().get(0));
    }

    @Test
    @DisplayName(value = "Should rename recurring bill successfully.")
    void renameRecurringBill() {
        RecurringBill recurringBill = populate();
        recurringBill.rename("NewName");

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillRenamedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertEquals("NewName", recurringBill.getName().getValue());
    }

    @Test
    @DisplayName(value = "Should change recurring bill description successfully.")
    void changeDescription() {
        RecurringBill recurringBill = populate();
        recurringBill.changeDescription("New description");

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillDescriptionChangedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertEquals("New description", recurringBill.getDescription().getValue());
    }

    @Test
    @DisplayName(value = "Should change recurring bill type successfully.")
    void changeType() {
        RecurringBill recurringBill = populate();
        recurringBill.changeType(RecurringBillType.INCOME);

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillTypeChangedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertEquals(RecurringBillType.INCOME, recurringBill.getType());
    }

    @Test
    @DisplayName(value = "Should change recurring bill recurrence successfully.")
    void changeRecurrence() {
        RecurringBill recurringBill = populate();
        recurringBill.changeRecurrence(4L);

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillRecurrenceChangedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertEquals(4L, recurringBill.getRecurrence().getValue());
    }

    @Test
    @DisplayName(value = "Should change recurring bill max periods successfully.")
    void changeMaxPeriods() {
        RecurringBill recurringBill = populate();
        recurringBill.changeMaxPeriods(60L);

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillMaxPeriodsChangedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertEquals(60L, recurringBill.getMaxPeriods().getValue());
    }

    @Test
    @DisplayName(value = "Should change recurring bill category successfully.")
    void changeCategory() {
        RecurringBill recurringBill = populate();
        UUID categoryId = UUID.randomUUID();
        recurringBill.changeCategory(categoryId);

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillCategoryChangedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertEquals(categoryId, recurringBill.getCategoryId());
    }

    @Test
    @DisplayName(value = "Should change recurring bill wallet successfully.")
    void changeWallet() {
        RecurringBill recurringBill = populate();
        UUID walletId = UUID.randomUUID();
        recurringBill.changeWallet(walletId);

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillWalletChangedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertEquals(walletId, recurringBill.getWalletId());
    }

    @Test
    @DisplayName(value = "Should execute recurring bill period successfully.")
    void executePeriod() {
        RecurringBill recurringBill = populate();
        recurringBill.nextPeriod(2L);

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillPeriodExecutedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertEquals(3L, recurringBill.getCurrentPeriods().getValue());
        Assertions.assertEquals(BigDecimal.valueOf(60.0), recurringBill.getTotalAmount().getValue());
        Assertions.assertEquals(LocalDate.now().plusDays(2), recurringBill.getNextPeriod());
    }

    @Test
    @DisplayName(value = "Should delete recurring bill successfully.")
    void deleteRecurringBill() {
        RecurringBill recurringBill = populate();
        recurringBill.delete();

        Assertions.assertEquals(2L, recurringBill.getVersion());
        Assertions.assertInstanceOf(RecurringBillDeletedEvent.class, recurringBill.getChanges().get(1));
        Assertions.assertTrue(recurringBill.getIsDeleted());
    }
}

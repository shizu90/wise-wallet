package dev.gabriel.wisewallet.bill.domain.models;

import dev.gabriel.wisewallet.bill.domain.events.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class BillTests {
    @Test
    @DisplayName("Should create bill successfully.")
    void createBill() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );

        Assertions.assertEquals(1L, bill.getVersion());
        Assertions.assertInstanceOf(BillCreatedEvent.class, bill.getChanges().get(0));
    }

    @Test
    @DisplayName("Should rename bill successfully.")
    void renameBill() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        bill.rename("NewName");

        Assertions.assertEquals(2L, bill.getVersion());
        Assertions.assertInstanceOf(BillRenamedEvent.class, bill.getChanges().get(1));
        Assertions.assertEquals("NewName", bill.getName().getValue());
    }

    @Test
    @DisplayName("Should change description successfully.")
    void changeDescription() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        bill.changeDescription("NewDescription");

        Assertions.assertEquals(2L, bill.getVersion());
        Assertions.assertInstanceOf(BillDescriptionChangedEvent.class, bill.getChanges().get(1));
        Assertions.assertEquals("NewDescription", bill.getDescription().getValue());
    }

    @Test
    @DisplayName("Should change type successfully.")
    void changeType() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        bill.changeType(BillType.EXPENSE);

        Assertions.assertEquals(2L, bill.getVersion());
        Assertions.assertInstanceOf(BillTypeChangedEvent.class, bill.getChanges().get(1));
        Assertions.assertEquals(BillType.EXPENSE, bill.getType());
    }

    @Test
    @DisplayName("Should update amount successfully.")
    void updateAmount() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        bill.updateAmount(BigDecimal.valueOf(200.0));

        Assertions.assertEquals(2L, bill.getVersion());
        Assertions.assertInstanceOf(BillAmountUpdatedEvent.class, bill.getChanges().get(1));
        Assertions.assertEquals(BigDecimal.valueOf(200.0), bill.getAmount().getValue());
    }

    @Test
    @DisplayName("Should change currency code successfully.")
    void changeCurrencyCode() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        bill.changeCurrencyCode("EUR");

        Assertions.assertEquals(2L, bill.getVersion());
        Assertions.assertInstanceOf(BillCurrencyCodeChangedEvent.class, bill.getChanges().get(1));
        Assertions.assertEquals("EUR", bill.getAmount().getCurrencyCode());
    }

    @Test
    @DisplayName("Should change category successfully.")
    void changeCategory() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        UUID categoryId = UUID.randomUUID();
        bill.changeCategory(categoryId);

        Assertions.assertEquals(2L, bill.getVersion());
        Assertions.assertInstanceOf(BillCategoryChangedEvent.class, bill.getChanges().get(1));
        Assertions.assertEquals(categoryId, bill.getCategoryId());
    }

    @Test
    @DisplayName("Should change wallet successfully.")
    void changeWallet() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        UUID walletId = UUID.randomUUID();
        bill.changeWallet(walletId);

        Assertions.assertEquals(2L, bill.getVersion());
        Assertions.assertInstanceOf(BillWalletChangedEvent.class, bill.getChanges().get(1));
        Assertions.assertEquals(walletId, bill.getWalletId());
    }

    @Test
    @DisplayName("Should delete bill successfully.")
    void deleteBill() {
        Bill bill = Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                BillType.INCOME,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        bill.delete();

        Assertions.assertEquals(2L, bill.getVersion());
        Assertions.assertInstanceOf(BillDeletedEvent.class, bill.getChanges().get(1));
        Assertions.assertTrue(bill.getIsDeleted());
    }
}

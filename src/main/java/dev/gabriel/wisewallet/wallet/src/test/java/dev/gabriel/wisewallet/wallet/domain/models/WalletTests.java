package dev.gabriel.wisewallet.wallet.domain.models;

import dev.gabriel.wisewallet.wallet.domain.events.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletTests {
    @Test
    @DisplayName("Should create wallet successfully.")
    void createWallet() {
        Wallet wallet = Wallet.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(3000.0),
                "BRL",
                WalletType.DEBIT_CARD,
                UUID.randomUUID()
        );

        Assertions.assertEquals(1L, wallet.getVersion());
        Assertions.assertInstanceOf(WalletCreatedEvent.class, wallet.getChanges().get(0));
    }

    @Test
    @DisplayName("Should rename wallet successfully.")
    void renameWallet() {
        Wallet wallet = Wallet.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(3000.0),
                "BRL",
                WalletType.DEBIT_CARD,
                UUID.randomUUID()
        );
        wallet.rename("WalletName");

        Assertions.assertEquals(2L, wallet.getVersion());
        Assertions.assertInstanceOf(WalletRenamedEvent.class, wallet.getChanges().get(1));
        Assertions.assertEquals("WalletName", wallet.getName().getValue());
    }

    @Test
    @DisplayName("Should change wallet description successfully.")
    void changeDescription() {
        Wallet wallet = Wallet.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(3000.0),
                "BRL",
                WalletType.DEBIT_CARD,
                UUID.randomUUID()
        );
        wallet.changeDescription("WalletDescription");

        Assertions.assertEquals(2L, wallet.getVersion());
        Assertions.assertInstanceOf(WalletDescriptionChangedEvent.class, wallet.getChanges().get(1));
        Assertions.assertEquals("WalletDescription", wallet.getDescription().getValue());
    }

    @Test
    @DisplayName("Should update wallet balance successfully.")
    void updateBalance() {
        Wallet wallet = Wallet.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(3000.0),
                "BRL",
                WalletType.DEBIT_CARD,
                UUID.randomUUID()
        );
        wallet.updateBalance(BigDecimal.valueOf(5000.0));

        Assertions.assertEquals(2L, wallet.getVersion());
        Assertions.assertInstanceOf(WalletBalanceUpdatedEvent.class, wallet.getChanges().get(1));
        Assertions.assertEquals(BigDecimal.valueOf(5000.0), wallet.getBalance().getValue());
    }

    @Test
    @DisplayName("Should change wallet currency code successfully.")
    void changeCurrencyCode() {
        Wallet wallet = Wallet.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(3000.0),
                "BRL",
                WalletType.DEBIT_CARD,
                UUID.randomUUID()
        );
        wallet.changeCurrencyCode("EUR");

        Assertions.assertEquals(2L, wallet.getVersion());
        Assertions.assertInstanceOf(WalletCurrencyCodeChangedEvent.class, wallet.getChanges().get(1));
        Assertions.assertEquals("EUR", wallet.getBalance().getCurrencyCode());
    }

    @Test
    @DisplayName("Should change wallet type successfully.")
    void changeType() {
        Wallet wallet = Wallet.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(3000.0),
                "BRL",
                WalletType.DEBIT_CARD,
                UUID.randomUUID()
        );
        wallet.changeType(WalletType.CASH);

        Assertions.assertEquals(2L, wallet.getVersion());
        Assertions.assertInstanceOf(WalletTypeChangedEvent.class, wallet.getChanges().get(1));
        Assertions.assertEquals(WalletType.CASH, wallet.getType());
    }

    @Test
    @DisplayName("Should delete wallet successfully.")
    void deleteWallet() {
        Wallet wallet = Wallet.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(3000.0),
                "BRL",
                WalletType.DEBIT_CARD,
                UUID.randomUUID()
        );
        wallet.delete();

        Assertions.assertEquals(2L, wallet.getVersion());
        Assertions.assertInstanceOf(WalletDeletedEvent.class, wallet.getChanges().get(1));
        Assertions.assertTrue(wallet.getIsDeleted());
    }
}

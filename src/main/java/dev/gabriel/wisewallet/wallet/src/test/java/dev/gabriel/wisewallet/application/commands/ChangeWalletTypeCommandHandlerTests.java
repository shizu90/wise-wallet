package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.domain.commands.ChangeWalletTypeCommand;
import dev.gabriel.wisewallet.domain.events.WalletTypeChangedEvent;
import dev.gabriel.wisewallet.domain.models.Wallet;
import dev.gabriel.wisewallet.domain.models.WalletType;
import dev.gabriel.wisewallet.domain.repositories.WalletRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class ChangeWalletTypeCommandHandlerTests {
    @Mock
    private WalletRepository walletRepository;

    @Autowired
    @InjectMocks
    private ChangeWalletTypeCommandHandler changeWalletTypeCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Change wallet type should work properly.")
    void changeType() {
        ChangeWalletTypeCommand command = new ChangeWalletTypeCommand(
                UUID.randomUUID(),
                WalletType.DEBIT_CARD
        );

        Wallet wallet = Wallet.create(
                command.getAggregateId(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                true,
                WalletType.CASH,
                UUID.randomUUID()
        );

        Mockito.when(walletRepository.load(command.getAggregateId())).thenReturn(Optional.of(wallet));

        Wallet returnedWallet = changeWalletTypeCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedWallet.getVersion());
        Assertions.assertInstanceOf(WalletTypeChangedEvent.class, returnedWallet.getChanges().get(1));
        Assertions.assertEquals(command.getType(), returnedWallet.getType());
    }
}

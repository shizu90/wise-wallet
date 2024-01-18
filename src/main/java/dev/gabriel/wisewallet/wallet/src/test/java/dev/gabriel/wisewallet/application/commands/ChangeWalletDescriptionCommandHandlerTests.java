package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.domain.commands.ChangeWalletDescriptionCommand;
import dev.gabriel.wisewallet.domain.commands.CreateWalletCommand;
import dev.gabriel.wisewallet.domain.events.WalletDescriptionChangedEvent;
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

public class ChangeWalletDescriptionCommandHandlerTests {
    @Mock
    private WalletRepository walletRepository;
    @Autowired
    @InjectMocks
    private ChangeWalletDescriptionCommandHandler changeWalletDescriptionCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Change wallet description should work properly.")
    void changeDescription() {
        ChangeWalletDescriptionCommand command = new ChangeWalletDescriptionCommand(
                UUID.randomUUID(),
                "NewDescription"
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

        Wallet returnedWallet = changeWalletDescriptionCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedWallet.getVersion());
        Assertions.assertInstanceOf(WalletDescriptionChangedEvent.class, returnedWallet.getChanges().get(1));
        Assertions.assertEquals(command.getDescription(), returnedWallet.getDescription().getValue());
    }
}

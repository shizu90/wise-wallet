package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.domain.commands.CreateWalletCommand;
import dev.gabriel.wisewallet.domain.events.WalletCreatedEvent;
import dev.gabriel.wisewallet.domain.models.Wallet;
import dev.gabriel.wisewallet.domain.models.WalletType;
import dev.gabriel.wisewallet.domain.repositories.WalletRepository;
import dev.gabriel.wisewallet.domain.services.CheckUniqueWalletName;
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
import java.util.UUID;

public class CreateWalletCommandHandlerTests {
    @Mock
    private CheckUniqueWalletName checkUniqueWalletName;

    @Mock
    private WalletRepository walletRepository;

    @Autowired
    @InjectMocks
    private CreateWalletCommandHandler createWalletCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create wallet should work properly.")
    void createWallet() {
        CreateWalletCommand command = new CreateWalletCommand(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                "BRL",
                true,
                WalletType.CASH,
                UUID.randomUUID()
        );

        Wallet wallet = Wallet.create(
                command.getAggregateId(),
                command.getName(),
                command.getDescription(),
                command.getBalance(),
                command.getCurrencyCode(),
                command.getMain(),
                command.getType(),
                command.getUserId()
        );

        Mockito.when(checkUniqueWalletName.exists(command.getUserId(), command.getName())).thenReturn(0L);

        Wallet returnedWallet = createWalletCommandHandler.handle(command);

        Assertions.assertEquals(1L, returnedWallet.getVersion());
        Assertions.assertInstanceOf(WalletCreatedEvent.class, returnedWallet.getChanges().get(0));
        Assertions.assertEquals(wallet.getName(), returnedWallet.getName());
    }
}

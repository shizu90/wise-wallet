package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.domain.commands.ToggleWalletMainCommand;
import dev.gabriel.wisewallet.domain.events.WalletMainToggledEvent;
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

public class ToggleWalletMainCommandHandlerTests {
    @Mock
    private WalletRepository walletRepository;

    @Autowired
    @InjectMocks
    private ToggleWalletMainCommandHandler toggleWalletMainCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Toggle main should work properly.")
    void toggleMain() {
        ToggleWalletMainCommand command = new ToggleWalletMainCommand(
                UUID.randomUUID(),
                false
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

        Wallet returnedWallet = toggleWalletMainCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedWallet.getVersion());
        Assertions.assertInstanceOf(WalletMainToggledEvent.class, returnedWallet.getChanges().get(1));
        Assertions.assertFalse(returnedWallet.getMain());
    }
}

package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.domain.commands.RenameWalletCommand;
import dev.gabriel.wisewallet.domain.events.WalletRenamedEvent;
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
import java.util.Optional;
import java.util.UUID;

public class RenameWalletCommandHandlerTests {
    @Mock
    private WalletRepository walletRepository;

    @Mock
    private CheckUniqueWalletName checkUniqueWalletName;

    @Autowired
    @InjectMocks
    private RenameWalletCommandHandler renameWalletCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Rename wallet should work properly.")
    void renameWallet() {
        RenameWalletCommand command = new RenameWalletCommand(
                UUID.randomUUID(),
                "NewName"
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

        Wallet returnedWallet = renameWalletCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedWallet.getVersion());
        Assertions.assertInstanceOf(WalletRenamedEvent.class, returnedWallet.getChanges().get(1));
        Assertions.assertEquals(command.getName(), returnedWallet.getName().getValue());
    }
}

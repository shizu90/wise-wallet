package dev.gabriel.wisewallet.application.commands;

import dev.gabriel.wisewallet.domain.commands.DeleteWalletCommand;
import dev.gabriel.wisewallet.domain.events.WalletDeletedEvent;
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

public class DeleteWalletCommandHandlerTests {
    @Mock
    private WalletRepository walletRepository;

    @Autowired
    @InjectMocks
    private DeleteWalletCommandHandler deleteWalletCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Delete wallet should work properly.")
    void deleteWallet() {
        DeleteWalletCommand command = new DeleteWalletCommand(
                UUID.randomUUID()
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

        Wallet returnedWallet = deleteWalletCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedWallet.getVersion());
        Assertions.assertInstanceOf(WalletDeletedEvent.class, returnedWallet.getChanges().get(1));
        Assertions.assertTrue(returnedWallet.getIsDeleted());
    }
}

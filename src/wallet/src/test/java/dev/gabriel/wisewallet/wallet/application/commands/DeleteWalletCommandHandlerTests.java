package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.wallet.domain.commands.DeleteWalletCommand;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
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

    private Wallet populate() {
        return Wallet.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(1200.0),
                "BRL",
                WalletType.DEBIT_CARD,
                UUID.randomUUID()
        );
    }

    @Test
    @DisplayName("Delete wallet command handler test")
    void deleteWallet() {
        Wallet wallet = populate();
        DeleteWalletCommand command = new DeleteWalletCommand(wallet.getId());

        Mockito.when(walletRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(wallet));

        Wallet returnedWallet = deleteWalletCommandHandler.handle(command);

        Assertions.assertTrue(returnedWallet.getIsDeleted());
    }
}

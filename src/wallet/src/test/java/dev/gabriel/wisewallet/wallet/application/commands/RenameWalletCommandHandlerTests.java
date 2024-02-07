package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.wallet.domain.commands.RenameWalletCommand;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import dev.gabriel.wisewallet.wallet.domain.services.CheckUniqueWalletName;
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
    @DisplayName("Rename wallet command handler tests.")
    void renameWallet() {
        Wallet wallet = populate();
        RenameWalletCommand command = new RenameWalletCommand(wallet.getId(), "NewName");

        Mockito.when(walletRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(wallet));
        Mockito.when(checkUniqueWalletName.exists(command.getName(), wallet.getUserId())).thenReturn(false);

        Wallet returnedWallet = renameWalletCommandHandler.handle(command);

        Assertions.assertEquals(command.getName(), returnedWallet.getName().getValue());
    }
}

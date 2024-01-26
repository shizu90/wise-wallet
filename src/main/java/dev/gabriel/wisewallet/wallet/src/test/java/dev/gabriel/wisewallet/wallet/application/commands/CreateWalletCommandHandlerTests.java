package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.wallet.domain.commands.CreateWalletCommand;
import dev.gabriel.wisewallet.wallet.domain.models.Wallet;
import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import dev.gabriel.wisewallet.wallet.domain.repositories.WalletRepository;
import dev.gabriel.wisewallet.wallet.domain.services.CheckUniqueWalletName;
import dev.gabriel.wisewallet.wallet.domain.services.CheckUserWallets;
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
    private WalletRepository walletRepository;
    @Mock
    private CheckUniqueWalletName checkUniqueWalletName;
    @Mock
    private CheckUserWallets checkUserWallets;
    @Autowired
    @InjectMocks
    private CreateWalletCommandHandler createWalletCommandHandler;

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
    @DisplayName("Create wallet command handler test")
    void createWallet() {
        Wallet wallet = populate();
        CreateWalletCommand command = new CreateWalletCommand(
                wallet.getId(),
                wallet.getName().getValue(),
                wallet.getDescription().getValue(),
                wallet.getBalance().getValue(),
                wallet.getBalance().getCurrencyCode(),
                wallet.getType(),
                wallet.getUserId()
        );

        Mockito.when(checkUniqueWalletName.exists(command.getName(), command.getUserId())).thenReturn(false);
        Mockito.when(checkUserWallets.getSize(command.getUserId())).thenReturn(0);

        Wallet returnedWallet = createWalletCommandHandler.handle(command);

        Assertions.assertEquals(command.getAggregateId(), returnedWallet.getId());
    }
}

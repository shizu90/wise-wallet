package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.wallet.domain.commands.ChangeWalletTypeCommand;
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
    @DisplayName("Change wallet type command handler test")
    void changeType() {
        Wallet wallet = populate();
        ChangeWalletTypeCommand command = new ChangeWalletTypeCommand(wallet.getId(), WalletType.CASH);

        Mockito.when(walletRepository.load(command.getAggregateId())).thenReturn(Optional.of(wallet));

        Wallet returnedWallet = changeWalletTypeCommandHandler.handle(command);

        Assertions.assertEquals(command.getType(), returnedWallet.getType());
    }
}

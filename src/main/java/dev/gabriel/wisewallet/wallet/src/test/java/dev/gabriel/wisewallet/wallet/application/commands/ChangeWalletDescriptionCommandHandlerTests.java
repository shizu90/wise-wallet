package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.wallet.domain.commands.ChangeWalletDescriptionCommand;
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
    @DisplayName("Change wallet description command handler test")
    void changeDescription() {
        Wallet wallet = populate();
        ChangeWalletDescriptionCommand command = new ChangeWalletDescriptionCommand(wallet.getId(), "NewDescription");

        Mockito.when(walletRepository.load(command.getAggregateId())).thenReturn(Optional.of(wallet));

        Wallet returnedWallet = changeWalletDescriptionCommandHandler.handle(command);

        Assertions.assertEquals(command.getDescription(), returnedWallet.getDescription().getValue());
    }
}

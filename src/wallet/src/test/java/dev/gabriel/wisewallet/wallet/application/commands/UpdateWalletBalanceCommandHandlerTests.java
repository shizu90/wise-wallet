package dev.gabriel.wisewallet.wallet.application.commands;

import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import dev.gabriel.wisewallet.wallet.domain.commands.UpdateWalletBalanceCommand;
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

public class UpdateWalletBalanceCommandHandlerTests {
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private CurrencyConversion currencyConversion;
    @Autowired
    @InjectMocks
    private UpdateWalletBalanceCommandHandler updateWalletBalanceCommandHandler;

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
    @DisplayName("Update wallet balance command handler test 1")
    void updateWalletBalance1() {
        Wallet wallet = populate();
        UpdateWalletBalanceCommand command = new UpdateWalletBalanceCommand(wallet.getId(), null, "EUR");

        Mockito.when(walletRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(wallet));
        Mockito.when(currencyConversion.convert(wallet.getBalance(), command.getCurrencyCode())).thenReturn(wallet.getBalance());

        Wallet returnedWallet = updateWalletBalanceCommandHandler.handle(command);

        Assertions.assertEquals(command.getCurrencyCode(), returnedWallet.getBalance().getCurrencyCode());
    }

    @Test
    @DisplayName("Update wallet balance command handler test 2")
    void updateWalletBalance2() {
        Wallet wallet = populate();
        UpdateWalletBalanceCommand command = new UpdateWalletBalanceCommand(wallet.getId(), BigDecimal.valueOf(4000), null);

        Mockito.when(walletRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(wallet));

        Wallet returnedWallet = updateWalletBalanceCommandHandler.handle(command);

        Assertions.assertEquals(command.getBalance(), returnedWallet.getBalance().getValue());
    }
}

package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.ChangeBillWalletCommand;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.models.BillType;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
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

public class ChangeBillWalletCommandHandlerTests {
    @Mock
    private BillRepository billRepository;
    @Autowired
    @InjectMocks
    private ChangeBillWalletCommandHandler changeBillWalletCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    private Bill populate() {
        return Bill.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                BigDecimal.valueOf(40.0),
                "BRL",
                BillType.EXPENSE,
                UUID.randomUUID(),
                UUID.randomUUID()
        );
    }

    @Test
    @DisplayName("Change bill wallet command handler test")
    void changeWallet() {
        Bill bill = populate();
        ChangeBillWalletCommand command = new ChangeBillWalletCommand(bill.getId(), UUID.randomUUID());

        Mockito.when(billRepository.load(command.getAggregateId())).thenReturn(Optional.of(bill));

        Bill returnedBill = changeBillWalletCommandHandler.handle(command);

        Assertions.assertEquals(command.getWalletId(), returnedBill.getWalletId());
    }
}

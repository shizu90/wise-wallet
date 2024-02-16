package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.ChangeBillDescriptionCommand;
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

public class ChangeBillDescriptionCommandHandlerTests {
    @Mock
    private BillRepository billRepository;
    @Autowired
    @InjectMocks
    private ChangeBillDescriptionCommandHandler changeBillDescriptionCommandHandler;

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
    @DisplayName("Change bill description command handler test")
    void changeDescription() {
        Bill bill = populate();
        ChangeBillDescriptionCommand command = new ChangeBillDescriptionCommand(bill.getId(), "NewDescription");

        Mockito.when(billRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(bill));

        Bill returnedBill = changeBillDescriptionCommandHandler.handle(command);

        Assertions.assertEquals(command.getDescription(), returnedBill.getDescription().getValue());
    }
}

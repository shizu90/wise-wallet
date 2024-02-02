package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.RenameBillCommand;
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

public class RenameBillCommandHandlerTests {
    @Mock
    private BillRepository billRepository;
    @Autowired
    @InjectMocks
    private RenameBillCommandHandler renameBillCommandHandler;

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
    @DisplayName("Rename bill command handler test")
    void renameBill() {
        Bill bill = populate();
        RenameBillCommand command = new RenameBillCommand(bill.getId(), "NewName");

        Mockito.when(billRepository.load(command.getAggregateId())).thenReturn(Optional.of(bill));

        Bill returnedBill = renameBillCommandHandler.handle(command);

        Assertions.assertEquals(command.getName(), returnedBill.getName().getValue());
    }
}

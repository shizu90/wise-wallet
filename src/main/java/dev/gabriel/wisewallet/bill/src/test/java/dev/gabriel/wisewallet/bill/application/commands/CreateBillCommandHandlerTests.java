package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.CreateBillCommand;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.models.BillType;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateBillCommandHandlerTests {
    @Mock
    private BillRepository billRepository;
    @Autowired
    @InjectMocks
    private CreateBillCommandHandler createBillCommandHandler;

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
    @DisplayName("Create bill command handler test")
    void createBill() {
        Bill bill = populate();
        CreateBillCommand command = new CreateBillCommand(
                bill.getId(),
                bill.getName().getValue(),
                bill.getDescription().getValue(),
                bill.getAmount().getValue(),
                bill.getAmount().getCurrencyCode(),
                bill.getType(),
                bill.getWalletId(),
                bill.getCategoryId()
        );

        Bill returnedBill = createBillCommandHandler.handle(command);

        Assertions.assertEquals(command.getAggregateId(), returnedBill.getId());
    }
}

package dev.gabriel.wisewallet.bill.application.commands;

import dev.gabriel.wisewallet.bill.domain.commands.UpdateBillAmountCommand;
import dev.gabriel.wisewallet.bill.domain.models.Bill;
import dev.gabriel.wisewallet.bill.domain.models.BillType;
import dev.gabriel.wisewallet.bill.domain.repositories.BillRepository;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
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

public class UpdateBillAmountCommandHandlerTests {
    @Mock
    private BillRepository billRepository;
    @Mock
    private CurrencyConversion currencyConversion;
    @Autowired
    @InjectMocks
    private UpdateBillAmountCommandHandler updateBillAmountCommandHandler;

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
    @DisplayName("Update bill amount command handler test 1")
    void updateAmount1() {
        Bill bill = populate();
        UpdateBillAmountCommand command = new UpdateBillAmountCommand(bill.getId(), null, "EUR");

        Mockito.when(billRepository.load(command.getAggregateId())).thenReturn(Optional.of(bill));

        Bill returnedBill = updateBillAmountCommandHandler.handle(command);

        Assertions.assertEquals(command.getCurrencyCode(), returnedBill.getAmount().getCurrencyCode());
    }

    @Test
    @DisplayName("Update bill amount command handler test 2")
    void updateAmount2() {
        Bill bill = populate();
        UpdateBillAmountCommand command = new UpdateBillAmountCommand(bill.getId(), BigDecimal.valueOf(20.0), null);

        Mockito.when(billRepository.load(command.getAggregateId())).thenReturn(Optional.of(bill));

        Bill returnedBill = updateBillAmountCommandHandler.handle(command);

        Assertions.assertEquals(command.getAmount(), returnedBill.getAmount().getValue());
    }
}

package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.UpdateBudgetAmountCommand;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.repositories.BudgetRepository;
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

public class UpdateBudgetAmountCommandHandlerTests {
    @Mock
    private BudgetRepository budgetRepository;
    @Mock
    private CurrencyConversion currencyConversion;
    @Autowired
    @InjectMocks
    private UpdateBudgetAmountCommandHandler updateBudgetAmountCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    private Budget populate() {
        return Budget.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                "BRL",
                UUID.randomUUID()
        );
    }

    @Test
    @DisplayName("Update budget amount command handler test 1")
    void updateAmount1() {
        Budget budget = populate();
        UpdateBudgetAmountCommand command = new UpdateBudgetAmountCommand(budget.getId(), null, "EUR");

        Mockito.when(budgetRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(budget));
        Mockito.when(currencyConversion.convert(budget.getAmount(), command.getCurrencyCode())).thenReturn(budget.getAmount());

        Budget returnedBudget = updateBudgetAmountCommandHandler.handle(command);

        Assertions.assertEquals(command.getCurrencyCode(), returnedBudget.getAmount().getCurrencyCode());
    }

    @Test
    @DisplayName("Update budget amount command handler test 2")
    void updateAmount2() {
        Budget budget = populate();
        UpdateBudgetAmountCommand command = new UpdateBudgetAmountCommand(budget.getId(), BigDecimal.valueOf(20.0), null);

        Mockito.when(budgetRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(budget));

        Budget returnedBudget = updateBudgetAmountCommandHandler.handle(command);

        Assertions.assertEquals(command.getAmount(), returnedBudget.getAmount().getValue());
    }
}

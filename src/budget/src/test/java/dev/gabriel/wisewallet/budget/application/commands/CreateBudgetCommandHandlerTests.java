package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.CreateBudgetCommand;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.repositories.BudgetRepository;
import dev.gabriel.wisewallet.budget.domain.services.CheckUniqueBudgetName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CreateBudgetCommandHandlerTests {
    @Mock
    private BudgetRepository budgetRepository;
    @Mock
    private CheckUniqueBudgetName checkUniqueBudgetName;
    @Autowired
    @InjectMocks
    private CreateBudgetCommandHandler createBudgetCommandHandler;

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
    @DisplayName("Create budget command handler test")
    void createBudget() {
        Budget budget = populate();
        CreateBudgetCommand command = new CreateBudgetCommand(
                budget.getId(),
                budget.getName().getValue(),
                budget.getDescription().getValue(),
                budget.getAmount().getCurrencyCode(),
                budget.getUserId()
        );

        Mockito.when(checkUniqueBudgetName.exists(command.getName(), command.getUserId())).thenReturn(false);

        Budget returnedBudget = createBudgetCommandHandler.handle(command);

        Assertions.assertEquals(command.getAggregateId(), returnedBudget.getId());
    }
}

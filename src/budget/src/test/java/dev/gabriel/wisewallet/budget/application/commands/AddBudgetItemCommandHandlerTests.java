package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.AddBudgetItemCommand;
import dev.gabriel.wisewallet.budget.domain.models.Budget;
import dev.gabriel.wisewallet.budget.domain.repositories.BudgetRepository;
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

public class AddBudgetItemCommandHandlerTests {
    @Mock
    private BudgetRepository budgetRepository;
    @Autowired
    @InjectMocks
    private AddBudgetItemCommandHandler addBudgetItemCommandHandler;

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
    @DisplayName("Add budget item command handler test")
    void addItem() {
        Budget budget = populate();
        AddBudgetItemCommand command = new AddBudgetItemCommand(
                budget.getId(),
                UUID.randomUUID(),
                "Name",
                BigDecimal.valueOf(50.0),
                "BRL",
                "EXPENSE"
        );

        Mockito.when(budgetRepository.load(command.getAggregateId())).thenReturn(Optional.of(budget));

        Budget returnedBudget = addBudgetItemCommandHandler.handle(command);

        Assertions.assertEquals(1, returnedBudget.getItems().size());
    }
}

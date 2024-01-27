package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.RemoveBudgetItemCommand;
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

public class RemoveBudgetItemCommandHandlerTests {
    @Mock
    private BudgetRepository budgetRepository;
    @Autowired
    @InjectMocks
    private RemoveBudgetItemCommandHandler removeBudgetItemCommandHandler;

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
    @DisplayName("Remove budget item command handler test")
    void removeItem() {
        Budget budget = populate();
        RemoveBudgetItemCommand command = new RemoveBudgetItemCommand(budget.getId(), UUID.randomUUID());
        budget.addItem(command.getBillId(), "Name", BigDecimal.valueOf(50.0), "BRL", "EXPENSE");

        Mockito.when(budgetRepository.load(command.getAggregateId())).thenReturn(Optional.of(budget));

        Budget returnedBudget = removeBudgetItemCommandHandler.handle(command);

        Assertions.assertEquals(0, returnedBudget.getItems().size());
    }
}

package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.RenameBudgetCommand;
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

import java.util.Optional;
import java.util.UUID;

public class RenameBudgetCommandHandlerTests {
    @Mock
    private BudgetRepository budgetRepository;
    @Mock
    private CheckUniqueBudgetName checkUniqueBudgetName;
    @Autowired
    @InjectMocks
    private RenameBudgetCommandHandler renameBudgetCommandHandler;

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
    @DisplayName("Rename budget command handler test")
    void renameBudget() {
        Budget budget = populate();
        RenameBudgetCommand command = new RenameBudgetCommand(budget.getId(), "NewName");

        Mockito.when(budgetRepository.load(command.getAggregateId())).thenReturn(Optional.of(budget));
        Mockito.when(checkUniqueBudgetName.exists(command.getName(), budget.getUserId())).thenReturn(false);

        Budget returnedBudget = renameBudgetCommandHandler.handle(command);

        Assertions.assertEquals(command.getName(), budget.getName().getValue());
    }
}

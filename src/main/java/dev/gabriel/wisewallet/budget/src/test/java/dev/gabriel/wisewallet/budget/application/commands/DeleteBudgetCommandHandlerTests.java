package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.DeleteBudgetCommand;
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

import java.util.Optional;
import java.util.UUID;

public class DeleteBudgetCommandHandlerTests {
    @Mock
    private BudgetRepository budgetRepository;
    @Autowired
    @InjectMocks
    private DeleteBudgetCommandHandler deleteBudgetCommandHandler;

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
    @DisplayName("Delete budget command handler test")
    void deleteBudget() {
        Budget budget = populate();
        DeleteBudgetCommand command = new DeleteBudgetCommand(budget.getId());

        Mockito.when(budgetRepository.load(command.getAggregateId())).thenReturn(Optional.of(budget));

        Budget returnedBudget = deleteBudgetCommandHandler.handle(command);

        Assertions.assertTrue(returnedBudget.getIsDeleted());
    }
}

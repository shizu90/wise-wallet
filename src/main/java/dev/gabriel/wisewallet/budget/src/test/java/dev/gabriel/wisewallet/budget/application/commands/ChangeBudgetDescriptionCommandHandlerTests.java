package dev.gabriel.wisewallet.budget.application.commands;

import dev.gabriel.wisewallet.budget.domain.commands.ChangeBudgetDescriptionCommand;
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

public class ChangeBudgetDescriptionCommandHandlerTests {
    @Mock
    private BudgetRepository budgetRepository;
    @Autowired
    @InjectMocks
    private ChangeBudgetDescriptionCommandHandler changeBudgetDescriptionCommandHandler;

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
    @DisplayName("Change budget description command handler test")
    void changeDescription() {
        Budget budget = populate();
        ChangeBudgetDescriptionCommand command = new ChangeBudgetDescriptionCommand(budget.getId(), "NewDescription");

        Mockito.when(budgetRepository.load(command.getAggregateId())).thenReturn(Optional.of(budget));

        Budget returnedBudget = changeBudgetDescriptionCommandHandler.handle(command);

        Assertions.assertEquals(command.getDescription(), returnedBudget.getDescription().getValue());
    }
}

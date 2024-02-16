package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.reminder.domain.commands.ExecuteReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
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

public class ExecuteReminderCommandHandlerTests {
    @Mock
    private ReminderRepository reminderRepository;
    @Autowired
    @InjectMocks
    private ExecuteReminderCommandHandler executeReminderCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    private Reminder populate() {
        return Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                40L,
                UUID.randomUUID()
        );
    }

    @Test
    @DisplayName("Execute reminder command handler test")
    void executeReminder() {
        Reminder reminder = populate();
        reminder.start();
        ExecuteReminderCommand command = new ExecuteReminderCommand(reminder.getId(), 1L);

        Mockito.when(reminderRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(reminder));

        Reminder returnedReminder = executeReminderCommandHandler.handle(command);

        Assertions.assertEquals(1L, returnedReminder.getCurrentRuns().getValue());
    }
}

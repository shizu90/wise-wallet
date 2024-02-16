package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.reminder.domain.commands.CreateReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
import dev.gabriel.wisewallet.reminder.domain.services.CheckUniqueReminderName;
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

public class CreateReminderCommandHandlerTests {
    @Mock
    private ReminderRepository reminderRepository;
    @Mock
    private CheckUniqueReminderName checkUniqueReminderName;
    @Autowired
    @InjectMocks
    private CreateReminderCommandHandler createReminderCommandHandler;

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
    @DisplayName("Create reminder command handler test")
    void createReminder() {
        Reminder reminder = populate();
        CreateReminderCommand command = new CreateReminderCommand(
                reminder.getId(),
                reminder.getName().getValue(),
                reminder.getDescription().getValue(),
                reminder.getRecurrence().getValue(),
                reminder.getMaxRuns().getValue(),
                reminder.getUserId()
        );

        Mockito.when(checkUniqueReminderName.exists(command.getName(), command.getUserId())).thenReturn(false);

        Reminder returnedReminder = createReminderCommandHandler.handle(command);

        Assertions.assertEquals(command.getAggregateId(), returnedReminder.getId());
    }
}

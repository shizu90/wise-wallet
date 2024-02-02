package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.reminder.domain.commands.ChangeReminderRecurrenceCommand;
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

public class ChangeReminderRecurrenceCommandHandlerTests {
    @Mock
    private ReminderRepository reminderRepository;
    @Autowired
    @InjectMocks
    private ChangeReminderRecurrenceCommandHandler changeReminderRecurrenceCommandHandler;

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
    @DisplayName("Change reminder recurrence command handler test")
    void changeRecurrence() {
        Reminder reminder = populate();
        ChangeReminderRecurrenceCommand command = new ChangeReminderRecurrenceCommand(reminder.getId(), 4L);

        Mockito.when(reminderRepository.load(command.getAggregateId())).thenReturn(Optional.of(reminder));

        Reminder returnedReminder = changeReminderRecurrenceCommandHandler.handle(command);

        Assertions.assertEquals(command.getRecurrence(), returnedReminder.getRecurrence().getValue());
    }
}

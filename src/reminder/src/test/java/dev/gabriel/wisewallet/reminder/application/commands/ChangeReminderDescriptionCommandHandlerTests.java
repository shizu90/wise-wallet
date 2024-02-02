package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.reminder.domain.commands.ChangeReminderDescriptionCommand;
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

public class ChangeReminderDescriptionCommandHandlerTests {
    @Mock
    private ReminderRepository reminderRepository;
    @Autowired
    @InjectMocks
    private ChangeReminderDescriptionCommandHandler changeReminderDescriptionCommandHandler;

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
    @DisplayName("Change reminder description command handler test")
    void changeReminderDescription() {
        Reminder reminder = populate();
        ChangeReminderDescriptionCommand command = new ChangeReminderDescriptionCommand(reminder.getId(), reminder.getDescription().getValue());

        Mockito.when(reminderRepository.load(command.getAggregateId())).thenReturn(Optional.of(reminder));

        Reminder returnedReminder = changeReminderDescriptionCommandHandler.handle(command);

        Assertions.assertEquals(command.getDescription(), returnedReminder.getDescription().getValue());
    }
}

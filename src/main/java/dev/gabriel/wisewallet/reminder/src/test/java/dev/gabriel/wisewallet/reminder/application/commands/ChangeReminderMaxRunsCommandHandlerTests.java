package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.reminder.domain.commands.ChangeReminderMaxRunsCommand;
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

public class ChangeReminderMaxRunsCommandHandlerTests {
    @Mock
    private ReminderRepository reminderRepository;
    @Autowired
    @InjectMocks
    private ChangeReminderMaxRunsCommandHandler changeReminderMaxRunsCommandHandler;

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
    @DisplayName("Change reminder max runs command handler test")
    void changeMaxRuns() {
        Reminder reminder = populate();
        ChangeReminderMaxRunsCommand command = new ChangeReminderMaxRunsCommand(reminder.getId(), 60L);

        Mockito.when(reminderRepository.load(command.getAggregateId())).thenReturn(Optional.of(reminder));

        Reminder returnedReminder = changeReminderMaxRunsCommandHandler.handle(command);

        Assertions.assertEquals(command.getMaxRuns(), returnedReminder.getMaxRuns().getValue());
    }
}

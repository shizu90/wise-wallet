package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.reminder.domain.commands.RenameReminderCommand;
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

import java.util.Optional;
import java.util.UUID;

public class RenameReminderCommandHandlerTests {
    @Mock
    private ReminderRepository reminderRepository;
    @Mock
    private CheckUniqueReminderName checkUniqueReminderName;
    @Autowired
    @InjectMocks
    private RenameReminderCommandHandler renameReminderCommandHandler;

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
    @DisplayName("Rename reminder command handler test")
    void renameReminder() {
        Reminder reminder = populate();
        RenameReminderCommand command = new RenameReminderCommand(reminder.getId(), reminder.getName().getValue());

        Mockito.when(reminderRepository.load(command.getAggregateId())).thenReturn(Optional.of(reminder));
        Mockito.when(checkUniqueReminderName.exists(command.getName(), command.getAggregateId())).thenReturn(false);

        Reminder returnedReminder = renameReminderCommandHandler.handle(command);

        Assertions.assertEquals(command.getName(), returnedReminder.getName().getValue());
    }
}

package dev.gabriel.wisewallet.reminder.domain.models;

import dev.gabriel.wisewallet.reminder.domain.events.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class ReminderTests {
    @Test
    @DisplayName("Should create reminder successfully.")
    void createReminder() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );

        Assertions.assertEquals(1L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderCreatedEvent.class, reminder.getChanges().get(0));
    }

    @Test
    @DisplayName("Should rename reminder successfully.")
    void rename() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );
        reminder.rename("NewName");

        Assertions.assertEquals(2L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderRenamedEvent.class, reminder.getChanges().get(1));
        Assertions.assertEquals("NewName", reminder.getName().getValue());
    }

    @Test
    @DisplayName("Should change reminder description successfully.")
    void changeDescription() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );
        reminder.changeDescription("NewDescription");

        Assertions.assertEquals(2L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderDescriptionChangedEvent.class, reminder.getChanges().get(1));
        Assertions.assertEquals("NewDescription", reminder.getDescription().getValue());
    }

    @Test
    @DisplayName("Should change reminder recurrence successfully.")
    void changeRecurrence() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );
        reminder.changeRecurrence(4L);

        Assertions.assertEquals(2L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderRecurrenceChangedEvent.class, reminder.getChanges().get(1));
        Assertions.assertEquals(4L, reminder.getRecurrence().getValue());
    }

    @Test
    @DisplayName("Should change reminder max runs successfully.")
    void changeMaxRuns() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );
        reminder.changeMaxRuns(40L);

        Assertions.assertEquals(2L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderMaxRunsChangedEvent.class, reminder.getChanges().get(1));
        Assertions.assertEquals(40L, reminder.getMaxRuns().getValue());
    }

    @Test
    @DisplayName("Should start reminder successfully.")
    void startReminder() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );
        reminder.start();

        Assertions.assertEquals(2L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderStartedEvent.class, reminder.getChanges().get(1));
        Assertions.assertTrue(reminder.getStarted());
    }

    @Test
    @DisplayName("Should stop reminder successfully.")
    void stopReminder() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );
        reminder.start();
        reminder.stop();

        Assertions.assertEquals(3L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderStoppedEvent.class, reminder.getChanges().get(2));
        Assertions.assertFalse(reminder.getStarted());
    }

    @Test
    @DisplayName("Should execute reminder successfully.")
    void executeReminder() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );
        reminder.start();
        reminder.execute(1L);

        Assertions.assertEquals(3L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderExecutedEvent.class, reminder.getChanges().get(2));
        Assertions.assertEquals(1L, reminder.getCurrentRuns().getValue());
        Assertions.assertEquals(LocalDate.now(), reminder.getLastRun());
    }

    @Test
    @DisplayName("Should delete reminder successfully.")
    void deleteReminder() {
        Reminder reminder = Reminder.create(
                UUID.randomUUID(),
                "Name",
                "Description",
                2L,
                20L,
                UUID.randomUUID()
        );
        reminder.delete();

        Assertions.assertEquals(2L, reminder.getVersion());
        Assertions.assertInstanceOf(ReminderDeletedEvent.class, reminder.getChanges().get(1));
        Assertions.assertTrue(reminder.getIsDeleted());
    }
}

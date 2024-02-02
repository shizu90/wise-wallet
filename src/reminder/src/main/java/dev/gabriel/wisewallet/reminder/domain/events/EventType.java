package dev.gabriel.wisewallet.reminder.domain.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum EventType {
    REMINDER_CREATED(ReminderCreatedEvent.class),
    REMINDER_RENAMED(ReminderRenamedEvent.class),
    REMINDER_DESCRIPTION_CHANGED(ReminderDescriptionChangedEvent.class),
    REMINDER_RECURRENCE_CHANGED(ReminderRecurrenceChangedEvent.class),
    REMINDER_MAX_RUNS_CHANGED(ReminderMaxRunsChangedEvent.class),
    REMINDER_STARTED(ReminderStartedEvent.class),
    REMINDER_STOPPED(ReminderStoppedEvent.class),
    REMINDER_RESET(ReminderResetEvent.class),
    REMINDER_EXECUTED(ReminderExecutedEvent.class),
    REMINDER_DELETED(ReminderDeletedEvent.class);

    private final Class<? extends ReminderEvent> type;
}

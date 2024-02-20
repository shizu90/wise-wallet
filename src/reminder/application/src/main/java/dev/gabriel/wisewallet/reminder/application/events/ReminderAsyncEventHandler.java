package dev.gabriel.wisewallet.reminder.application.events;

import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;

public interface ReminderAsyncEventHandler {
    void handle(UserDeletedEvent event);
}

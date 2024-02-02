package dev.gabriel.wisewallet.reminder.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class ReminderReachedMaxRunsException extends DomainException {
    public ReminderReachedMaxRunsException(String msg) {
        super(msg);
    }
}

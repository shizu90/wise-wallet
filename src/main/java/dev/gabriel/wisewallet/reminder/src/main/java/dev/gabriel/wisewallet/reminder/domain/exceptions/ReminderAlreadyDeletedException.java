package dev.gabriel.wisewallet.reminder.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class ReminderAlreadyDeletedException extends DomainException {
    public ReminderAlreadyDeletedException(String msg) {
        super(msg);
    }
}

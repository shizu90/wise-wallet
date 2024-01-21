package dev.gabriel.wisewallet.reminder.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class ReminderAlreadyExistsException extends DomainException {
    public ReminderAlreadyExistsException(String msg) {
        super(msg);
    }
}

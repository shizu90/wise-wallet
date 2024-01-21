package dev.gabriel.wisewallet.reminder.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class ReminderNotStartedException extends DomainException {
    public ReminderNotStartedException(String msg) {
        super(msg);
    }
}

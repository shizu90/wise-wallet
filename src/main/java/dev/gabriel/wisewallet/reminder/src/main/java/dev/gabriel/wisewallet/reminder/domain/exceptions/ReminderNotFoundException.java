package dev.gabriel.wisewallet.reminder.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class ReminderNotFoundException extends DomainException {
    public ReminderNotFoundException(String msg) {
        super(msg);
    }
}

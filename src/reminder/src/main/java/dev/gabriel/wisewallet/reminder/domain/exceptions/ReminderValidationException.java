package dev.gabriel.wisewallet.reminder.domain.exceptions;

import dev.gabriel.wisewallet.core.domain.exceptions.DomainException;

public class ReminderValidationException extends DomainException {
    public ReminderValidationException(String msg) {
        super(msg);
    }
}

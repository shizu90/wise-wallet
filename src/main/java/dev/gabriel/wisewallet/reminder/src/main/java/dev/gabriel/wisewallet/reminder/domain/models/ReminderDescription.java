package dev.gabriel.wisewallet.reminder.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
@Getter
public class ReminderDescription extends ValueObject {
    private final String value;

    public static ReminderDescription create(String value) {
        return new ReminderDescription(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new ReminderValidationException("Reminder validation failed on description field: description must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

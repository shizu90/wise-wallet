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
public class ReminderName extends ValueObject {
    private final String value;

    public static ReminderName create(String value) {
        return new ReminderName(value);
    }

    public static void validate(String value) {
        if(value == null || value.isEmpty() || value.length() > 256) {
            throw new ReminderValidationException("Reminder validation failed on name field: name must have between 1 and 256 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

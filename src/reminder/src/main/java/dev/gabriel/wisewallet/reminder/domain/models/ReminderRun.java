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
public class ReminderRun extends ValueObject {
    private final Long value;

    public static ReminderRun create(Long value) {
        return new ReminderRun(value);
    }

    public static void validate(Long value) {
        if(value == null) {
            throw new ReminderValidationException("Reminder validation failed on run field: not a valid run.");
        }
    }
    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

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
public class ReminderRecurrence extends ValueObject {
    private final Long value;

    public static ReminderRecurrence create(Long value) {
        return new ReminderRecurrence(value);
    }

    public static void validate(Long value) {
        if(value == null || value <= 0) {
            throw new ReminderValidationException("Reminder validation failed on recurrence field: recurrence must be greater than 0.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

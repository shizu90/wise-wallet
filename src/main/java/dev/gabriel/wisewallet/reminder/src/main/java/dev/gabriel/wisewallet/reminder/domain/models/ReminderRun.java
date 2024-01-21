package dev.gabriel.wisewallet.reminder.domain.models;

import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReminderRun extends ValueObject {
    private final Long value;

    public static ReminderRun create(Long value) {
        return new ReminderRun(value);
    }

    public static void validate(Long value) {
        if(value == null || value < 0) {
            throw new ReminderValidationException("Reminder validation failed on run field: run must be greater or equals to 0.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

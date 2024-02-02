package dev.gabriel.wisewallet.recurringbill.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
public class RecurringBillRecurrence extends ValueObject {
    private final Long value;

    public static RecurringBillRecurrence create(Long value) {
        return new RecurringBillRecurrence(value);
    }

    public static void validate(Long value) {
        if(value == null || value == 0) {
            throw new RecurringBillValidationException("Recurring bill validation failed on recurrence field: not a valid recurrence.");
        }
    }
    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

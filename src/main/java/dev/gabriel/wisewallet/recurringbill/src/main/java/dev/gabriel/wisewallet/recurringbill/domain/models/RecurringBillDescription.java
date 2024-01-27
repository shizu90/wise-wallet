package dev.gabriel.wisewallet.recurringbill.domain.models;

import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RecurringBillDescription extends ValueObject {
    private final String value;

    public static RecurringBillDescription create(String value) {
        return new RecurringBillDescription(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new RecurringBillValidationException("Recurring bill validation failed on description field: description must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

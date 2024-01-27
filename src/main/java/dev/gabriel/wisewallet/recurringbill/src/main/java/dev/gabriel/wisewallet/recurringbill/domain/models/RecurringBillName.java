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
public class RecurringBillName extends ValueObject {
    private final String value;

    public static RecurringBillName create(String value) {
        return new RecurringBillName(value);
    }

    public static void validate(String value) {
        if(value == null || value.isBlank() || value.length() > 256) {
            throw new RecurringBillValidationException("Recurring bill validation failed on name field: name must have between 1 and 256 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

package dev.gabriel.wisewallet.recurringbill.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
@Getter
public class RecurringBillPeriod extends ValueObject {
    private final Long value;

    public static RecurringBillPeriod create(Long value) {
        return new RecurringBillPeriod(value);
    }

    public static void validate(Long value) {
        if(value == null) {
            throw new RecurringBillValidationException("Recurring bill validation failed on period field: not a valid period.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

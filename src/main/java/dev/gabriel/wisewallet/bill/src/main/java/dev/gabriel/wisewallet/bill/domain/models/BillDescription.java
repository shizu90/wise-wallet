package dev.gabriel.wisewallet.bill.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.bill.domain.exceptions.BillValidationException;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
public class BillDescription extends ValueObject {
    private final String value;

    public static BillDescription create(String value) {
        return new BillDescription(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new BillValidationException("Bill validation failed on description field: description must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

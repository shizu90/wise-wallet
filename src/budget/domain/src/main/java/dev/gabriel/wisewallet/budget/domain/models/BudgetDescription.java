package dev.gabriel.wisewallet.budget.domain.models;

import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetValidationException;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BudgetDescription extends ValueObject {
    private final String value;

    public static BudgetDescription create(String value) {
        return new BudgetDescription(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new BudgetValidationException("Budget validation failed on description field: description must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}



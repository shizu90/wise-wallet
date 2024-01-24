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
public class BudgetName extends ValueObject {
    private final String value;

    public static BudgetName create(String value) {
        return new BudgetName(value);
    }

    public static void validate(String value) {
        if(value == null || value.isEmpty() || value.length() > 256) {
            throw new BudgetValidationException("Budget validation failed on name field: name must have between 1 and 256 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

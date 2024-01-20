package dev.gabriel.wisewallet.category.domain.models;

import dev.gabriel.wisewallet.category.domain.exceptions.CategoryValidationException;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryName extends ValueObject {
    private final String value;

    public static CategoryName create(String value) {
        return new CategoryName(value);
    }

    public static void validate(String value) {
        if(value == null || value.isEmpty() || value.length() > 256) {
            throw new CategoryValidationException("Category validation failed on name field: name must have between 1 and 256 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

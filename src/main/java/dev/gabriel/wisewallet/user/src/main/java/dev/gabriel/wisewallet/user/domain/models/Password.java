package dev.gabriel.wisewallet.user.domain.models;

import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.user.domain.exceptions.UserValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Password extends ValueObject {
    private final String value;

    public static Password create(String value) {
        return new Password(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() < 6 || value.length() > 24) {
            throw new UserValidationException("User validation failed on password field: password must have between 6 and 24 characters");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

package dev.gabriel.wisewallet.user.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.user.domain.exceptions.UserValidationException;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
@Getter
public class Username extends ValueObject {
    private final String value;

    public static Username create(String value) {
        return new Username(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() < 3 || value.length() > 128) {
            throw new UserValidationException("User validation failed on name field: name must have between 3 and 128 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

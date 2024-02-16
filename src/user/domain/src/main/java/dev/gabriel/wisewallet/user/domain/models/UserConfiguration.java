package dev.gabriel.wisewallet.user.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.user.domain.exceptions.UserValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
@Getter
public class UserConfiguration extends ValueObject {
    private final String defaultCurrencyCode;
    private final String defaultLanguage;

    public static UserConfiguration create(String defaultCurrencyCode, String defaultLanguage) {
        return new UserConfiguration(defaultCurrencyCode, defaultLanguage);
    }

    public static void validateCurrencyCode(String defaultCurrencyCode) {
        if(defaultCurrencyCode == null || defaultCurrencyCode.length() != 3) {
            throw new UserValidationException("User validation failed on default currency code field: not a valid currency code.");
        }
    }

    public static void validateLanguage(String defaultLanguage) {
        if(defaultLanguage == null) {
            throw new UserValidationException("User validation failed on default language field: not a valid language code.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {defaultCurrencyCode, defaultLanguage});
    }
}

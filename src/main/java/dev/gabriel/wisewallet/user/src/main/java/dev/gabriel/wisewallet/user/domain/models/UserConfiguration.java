package dev.gabriel.wisewallet.user.domain.models;

import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserConfiguration extends ValueObject {
    private final String defaultCurrencyCode;
    private final String defaultLanguage;

    public static UserConfiguration create(String defaultCurrencyCode, String defaultLanguage) {
        return new UserConfiguration(defaultCurrencyCode, defaultLanguage);
    }

    public boolean isNull() {
        return defaultLanguage == null && defaultCurrencyCode == null;
    }

    public boolean isEmpty() {
        return defaultLanguage.isEmpty() && defaultCurrencyCode.isEmpty();
    }

    public boolean isBlank() {
        return defaultLanguage.isBlank() && defaultCurrencyCode.isBlank();
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {defaultCurrencyCode, defaultLanguage});
    }
}

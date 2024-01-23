package dev.gabriel.wisewallet.user.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
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

    @JsonIgnore
    public boolean isNull() {
        return defaultLanguage == null && defaultCurrencyCode == null;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return defaultLanguage.isEmpty() && defaultCurrencyCode.isEmpty();
    }

    @JsonIgnore
    public boolean isBlank() {
        return defaultLanguage.isBlank() && defaultCurrencyCode.isBlank();
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {defaultCurrencyCode, defaultLanguage});
    }
}

package dev.gabriel.wisewallet.domain.models;

import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.domain.exceptions.WalletValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class WalletName extends ValueObject {
    private final String value;

    public static WalletName create(String value) {
        return new WalletName(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 255) {
            throw new WalletValidationException("Wallet validation failed on name field: name must have less than 255 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

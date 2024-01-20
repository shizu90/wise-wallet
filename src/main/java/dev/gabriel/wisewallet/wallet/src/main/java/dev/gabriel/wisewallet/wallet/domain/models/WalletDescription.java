package dev.gabriel.wisewallet.wallet.domain.models;

import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WalletDescription extends ValueObject {
    private final String value;

    public static WalletDescription create(String value) {
        return new WalletDescription(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new WalletValidationException("Wallet validation failed on description field: description must have less than 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

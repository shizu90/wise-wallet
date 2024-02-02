package dev.gabriel.wisewallet.wallet.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
@Getter
public class WalletName extends ValueObject {
    private final String value;

    public static WalletName create(String value) {
        return new WalletName(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 256) {
            throw new WalletValidationException("Wallet validation failed on name field: name must have between 1 and 256 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

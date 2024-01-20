package dev.gabriel.wisewallet.wallet.currency.domain.models;

import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Currency extends ValueObject {
    private final BigDecimal value;
    private final String currencyCode;

    public static Currency create(BigDecimal value, String currencyCode) {
        return new Currency(value, currencyCode);
    }

    public Currency add(Currency currency) {
        return new Currency(value.add(currency.getValue()), currencyCode);
    }

    public Currency subtract(Currency currency) {
        return new Currency(value.subtract(currency.getValue()), currencyCode);
    }

    public Currency multiply(long n) {
        return new Currency(value.multiply(BigDecimal.valueOf(n)), currencyCode);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value, currencyCode});
    }
}

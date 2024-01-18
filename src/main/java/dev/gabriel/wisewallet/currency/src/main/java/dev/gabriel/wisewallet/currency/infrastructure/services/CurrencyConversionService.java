package dev.gabriel.wisewallet.currency.infrastructure.services;

import dev.gabriel.wisewallet.currency.domain.models.Currency;
import dev.gabriel.wisewallet.currency.domain.services.CurrencyConversion;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService implements CurrencyConversion {
    @Override
    public Currency convert(Currency fromCurrency, String toCurrencyCode) {
        return Currency.create(fromCurrency.getValue(), toCurrencyCode);
    }
}

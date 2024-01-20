package dev.gabriel.wisewallet.wallet.currency.infrastructure.services;

import dev.gabriel.wisewallet.wallet.currency.domain.models.Currency;
import dev.gabriel.wisewallet.wallet.currency.domain.services.CurrencyConversion;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService implements CurrencyConversion {
    @Override
    public Currency convert(Currency fromCurrency, String toCurrencyCode) {
        return Currency.create(fromCurrency.getValue(), toCurrencyCode);
    }
}

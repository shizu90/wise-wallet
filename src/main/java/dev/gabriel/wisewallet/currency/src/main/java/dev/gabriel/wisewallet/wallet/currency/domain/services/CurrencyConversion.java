package dev.gabriel.wisewallet.wallet.currency.domain.services;

import dev.gabriel.wisewallet.core.domain.services.DomainService;
import dev.gabriel.wisewallet.wallet.currency.domain.models.Currency;

public interface CurrencyConversion extends DomainService {
    Currency convert(Currency fromCurrency, String toCurrencyCode);
}

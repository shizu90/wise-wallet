package dev.gabriel.wisewallet.wallet.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.currency.domain.models.Currency;
import dev.gabriel.wisewallet.wallet.domain.events.*;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletAlreadyDeletedException;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
public class Wallet extends Aggregate {
    private WalletName name;
    private WalletDescription description;
    private Currency balance;
    private WalletType type;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    @JsonCreator
    private Wallet(@NonNull UUID id, Long version) {
        super(id, version);
    }

    private Wallet(UUID id,
                   String name,
                   String description,
                   BigDecimal balance,
                   String currencyCode,
                   WalletType type,
                   UUID userId
    ) {
        this(id, 0L);

        applyChange(new WalletCreatedEvent(
                id,
                getNextVersion(),
                name,
                description,
                balance,
                currencyCode,
                type,
                userId
        ));
    }

    public static Wallet create(UUID id,
                                String name,
                                String description,
                                BigDecimal balance,
                                String currencyCode,
                                WalletType type,
                                UUID userId
    ) {
        WalletName.validate(name);
        WalletDescription.validate(description);

        return new Wallet(id, name, description, balance, currencyCode, type, userId);
    }

    public void rename(String name) {
        WalletName.validate(name);

        applyChange(new WalletRenamedEvent(
                id,
                getNextVersion(),
                name
        ));
    }

    public void changeDescription(String description) {
        WalletDescription.validate(description);

        applyChange(new WalletDescriptionChangedEvent(
                id,
                getNextVersion(),
                description
        ));
    }

    public void updateBalance(BigDecimal balance) {
        applyChange(new WalletBalanceUpdatedEvent(
                id,
                getNextVersion(),
                balance
        ));
    }

    public void changeCurrencyCode(String currencyCode) {
        applyChange(new WalletCurrencyCodeChangedEvent(
                id,
                getNextVersion(),
                currencyCode
        ));
    }

    public void changeType(WalletType type) {
        applyChange(new WalletTypeChangedEvent(
                id,
                getNextVersion(),
                type
        ));
    }

    public void delete() {
        if(isDeleted)
            throw new WalletAlreadyDeletedException("Wallet %s already deleted.".formatted(id));

        applyChange(new WalletDeletedEvent(
                id,
                getNextVersion()
        ));
    }

    @SuppressWarnings("unused")
    private void apply(WalletCreatedEvent event) {
        this.name = WalletName.create(event.getName());
        this.description = WalletDescription.create(event.getDescription());
        this.balance = Currency.create(event.getBalance(), event.getCurrencyCode());
        this.type = event.getType();
        this.userId = event.getUserId();
        this.createdAt = Instant.now();
        this.updatedAt = null;
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(WalletRenamedEvent event) {
        this.name = WalletName.create(event.getName());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(WalletDescriptionChangedEvent event) {
        this.description = WalletDescription.create(event.getDescription());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(WalletBalanceUpdatedEvent event) {
        this.balance = Currency.create(event.getBalance(), balance.getCurrencyCode());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(WalletCurrencyCodeChangedEvent event) {
        this.balance = Currency.create(balance.getValue(), event.getCurrencyCode());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(WalletTypeChangedEvent event) {
        this.type = event.getType();
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(WalletDeletedEvent event) {
        this.isDeleted = true;
        this.updatedAt = Instant.now();
    }

    @Override
    public String getAggregateType() {
        return AggregateType.WALLET.toString();
    }
}

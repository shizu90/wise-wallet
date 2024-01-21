package dev.gabriel.wisewallet.wallet.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.currency.domain.models.Currency;
import dev.gabriel.wisewallet.wallet.domain.events.*;
import dev.gabriel.wisewallet.wallet.domain.exceptions.WalletAlreadyDeletedException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Wallet extends Aggregate {
    private WalletName name;
    private WalletDescription description;
    private Currency balance;
    private Currency initialBalance;
    private Boolean main;
    private WalletType type;
    private UUID userId;
    private Boolean isDeleted;

    @JsonCreator
    public Wallet(UUID id, Long version) {
        super(WalletId.create(id), version);
    }

    private Wallet(UUID id,
                   String name,
                   String description,
                   BigDecimal balance,
                   String currencyCode,
                   Boolean main,
                   WalletType type,
                   UUID userId
    ) {
        this(id, 0L);
        applyChange(new WalletCreatedEvent(
                id,
                this.getNextVersion(),
                name,
                description,
                balance,
                currencyCode,
                main,
                type,
                userId
        ));
    }

    public static Wallet create(UUID id,
                                String name,
                                String description,
                                BigDecimal balance,
                                String currencyCode,
                                Boolean main,
                                WalletType type,
                                UUID userId
    ) {
        WalletName.validate(name);
        WalletDescription.validate(description);

        return new Wallet(id, name, description, balance, currencyCode, main, type, userId);
    }

    public void rename(String name) {
        WalletName.validate(name);

        applyChange(new WalletRenamedEvent(
                id.getValue(),
                getNextVersion(),
                name
        ));
    }

    public void changeDescription(String description) {
        WalletDescription.validate(description);

        applyChange(new WalletDescriptionChangedEvent(
                id.getValue(),
                getNextVersion(),
                description
        ));
    }

    public void updateBalance(BigDecimal balance) {
        applyChange(new WalletBalanceUpdatedEvent(
                id.getValue(),
                getNextVersion(),
                balance
        ));
    }

    public void changeCurrencyCode(String currencyCode) {
        applyChange(new WalletCurrencyCodeChangedEvent(
                id.getValue(),
                getNextVersion(),
                currencyCode
        ));
    }

    public void changeType(WalletType type) {
        applyChange(new WalletTypeChangedEvent(
                id.getValue(),
                getNextVersion(),
                type
        ));
    }

    public void toggleMain(Boolean main) {
        applyChange(new WalletMainToggledEvent(
                id.getValue(),
                getNextVersion(),
                main
        ));
    }

    public void delete() {
        if(isDeleted)
            throw new WalletAlreadyDeletedException("Wallet %s already deleted.".formatted(id.getValue()));

        applyChange(new WalletDeletedEvent(
                id.getValue(),
                getNextVersion()
        ));
    }

    @SuppressWarnings("unused")
    private void apply(WalletCreatedEvent event) {
        this.name = WalletName.create(event.getName());
        this.description = WalletDescription.create(event.getDescription());
        this.balance = Currency.create(event.getBalance(), event.getCurrencyCode());
        this.initialBalance = balance;
        this.main = event.getMain();
        this.type = event.getType();
        this.userId = event.getUserId();
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(WalletRenamedEvent event) {
        this.name = WalletName.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(WalletDescriptionChangedEvent event) {
        this.description = WalletDescription.create(event.getDescription());
    }

    @SuppressWarnings("unused")
    private void apply(WalletBalanceUpdatedEvent event) {
        this.balance = Currency.create(event.getBalance(), balance.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(WalletCurrencyCodeChangedEvent event) {
        this.balance = Currency.create(balance.getValue(), event.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(WalletTypeChangedEvent event) {
        this.type = event.getType();
    }

    @SuppressWarnings("unused")
    private void apply(WalletMainToggledEvent event) {
        this.main = event.getMain();
    }

    @SuppressWarnings("unused")
    private void apply(WalletDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public String getAggregateType() {
        return AggregateType.WALLET.toString();
    }

    @Override
    public WalletId getId() {
        return (WalletId) this.id;
    }
}

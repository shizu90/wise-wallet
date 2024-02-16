package dev.gabriel.wisewallet.bill.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.bill.domain.events.*;
import dev.gabriel.wisewallet.bill.domain.exceptions.BillAlreadyDeletedException;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.currency.domain.models.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
public class Bill extends Aggregate {
    private BillName name;
    private BillDescription description;
    private Currency amount;
    private BillType type;
    private UUID walletId;
    private UUID categoryId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    @JsonCreator
    public Bill(UUID id, Long version) {
        super(id, version);
    }

    private Bill(UUID id,
                 String name,
                 String description,
                 BigDecimal amount,
                 String currencyCode,
                 BillType type,
                 UUID walletId,
                 UUID categoryId
    ) {
        this(id, 0L);

        BillName.validate(name);
        BillDescription.validate(description);
        applyChange(new BillCreatedEvent(
                id,
                getNextVersion(),
                name,
                description,
                amount,
                currencyCode,
                type,
                walletId,
                categoryId
        ));
    }

    public static Bill create(UUID id,
                              String name,
                              String description,
                              BigDecimal amount,
                              String currencyCode,
                              BillType type,
                              UUID walletId,
                              UUID categoryId
    ) {
        return new Bill(id, name, description, amount, currencyCode, type, walletId, categoryId);
    }

    public void rename(String name) {
        BillName.validate(name);

        applyChange(new BillRenamedEvent(
                id,
                getNextVersion(),
                name
        ));
    }

    public void changeDescription(String description) {
        BillDescription.validate(description);

        applyChange(new BillDescriptionChangedEvent(
                id,
                getNextVersion(),
                description
        ));
    }

    public void updateAmount(BigDecimal amount) {
        applyChange(new BillAmountUpdatedEvent(
                id,
                getNextVersion(),
                amount
        ));
    }

    public void changeCurrencyCode(String currencyCode) {
        applyChange(new BillCurrencyCodeChangedEvent(
                id,
                getNextVersion(),
                currencyCode
        ));
    }

    public void changeType(BillType type) {
        applyChange(new BillTypeChangedEvent(
                id,
                getNextVersion(),
                type
        ));
    }

    public void changeWallet(UUID walletId) {
        applyChange(new BillWalletChangedEvent(
                id,
                getNextVersion(),
                walletId
        ));
    }

    public void changeCategory(UUID categoryId) {
        applyChange(new BillCategoryChangedEvent(
                id,
                getNextVersion(),
                categoryId
        ));
    }

    public void delete() {
        if(isDeleted)
            throw new BillAlreadyDeletedException("Bill %s already deleted.".formatted(id));

        applyChange(new BillDeletedEvent(
                id,
                getNextVersion()
        ));
    }

    @SuppressWarnings("unused")
    private void apply(BillCreatedEvent event) {
        this.id = event.getAggregateId();
        this.name = BillName.create(event.getName());
        this.description = BillDescription.create(event.getDescription());
        this.amount = Currency.create(event.getAmount(), event.getCurrencyCode());
        this.type = event.getType();
        this.categoryId = event.getCategoryId();
        this.walletId = event.getWalletId();
        this.createdAt = Instant.now();
        this.updatedAt = null;
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(BillRenamedEvent event) {
        this.name = BillName.create(event.getName());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BillDescriptionChangedEvent event) {
        this.description = BillDescription.create(event.getDescription());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BillAmountUpdatedEvent event) {
        this.amount = Currency.create(event.getAmount(), amount.getCurrencyCode());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BillCurrencyCodeChangedEvent event) {
        this.amount = Currency.create(amount.getValue(), event.getCurrencyCode());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BillTypeChangedEvent event) {
        this.type = event.getType();
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BillCategoryChangedEvent event) {
        this.categoryId = event.getCategoryId();
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BillWalletChangedEvent event) {
        this.walletId = event.getWalletId();
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BillDeletedEvent event) {
        this.isDeleted = true;
        this.updatedAt = Instant.now();
    }

    @Override
    public String getAggregateType() {
        return AggregateType.BILL.toString();
    }
}

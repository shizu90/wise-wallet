package dev.gabriel.wisewallet.recurringbill.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.currency.domain.models.Currency;
import dev.gabriel.wisewallet.recurringbill.domain.events.*;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillAlreadyDeletedException;
import dev.gabriel.wisewallet.recurringbill.domain.exceptions.RecurringBillReachedMaxPeriodsException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class RecurringBill extends Aggregate {
    private RecurringBillName name;
    private RecurringBillDescription description;
    private Currency amount;
    private RecurringBillRecurrence recurrence;
    private RecurringBillType type;
    private RecurringBillPeriod maxPeriods;
    private RecurringBillPeriod currentPeriods;
    private LocalDate lastPeriod;
    private UUID walletId;
    private UUID categoryId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    @JsonCreator
    private RecurringBill(UUID id, Long version) {
        super(id, version);
    }

    private RecurringBill(UUID id,
                          String name,
                          String description,
                          BigDecimal amount,
                          String currencyCode,
                          Long recurrence,
                          RecurringBillType type,
                          Long maxPeriods,
                          UUID walletId,
                          UUID categoryId
    ) {
        this(id, 0L);

        RecurringBillName.validate(name);
        RecurringBillDescription.validate(description);
        RecurringBillRecurrence.validate(recurrence);
        RecurringBillPeriod.validate(maxPeriods);
        applyChange(new RecurringBillCreatedEvent(
                id,
                getNextVersion(),
                name,
                description,
                amount,
                currencyCode,
                recurrence,
                type,
                maxPeriods,
                walletId,
                categoryId
        ));
    }

    public static RecurringBill create(UUID id,
                                       String name,
                                       String description,
                                       BigDecimal amount,
                                       String currencyCode,
                                       Long recurrence,
                                       RecurringBillType type,
                                       Long maxPeriods,
                                       UUID walletId,
                                       UUID categoryId
    ) {
        return new RecurringBill(id, name, description, amount, currencyCode, recurrence, type, maxPeriods, walletId, categoryId);
    }

    public void rename(String name) {
        RecurringBillName.validate(name);

        applyChange(new RecurringBillRenamedEvent(id, getNextVersion(), name));
    }

    public void changeDescription(String description) {
        RecurringBillDescription.create(description);

        applyChange(new RecurringBillDescriptionChangedEvent(id, getNextVersion(), description));
    }

    public void updateAmount(BigDecimal amount) {
        applyChange(new RecurringBillAmountUpdatedEvent(id, getNextVersion(), amount));
    }

    public void changeCurrencyCode(String currencyCode) {
        applyChange(new RecurringBillCurrencyCodeChangedEvent(id, getNextVersion(), currencyCode));
    }

    public void changeRecurrence(Long recurrence) {
        applyChange(new RecurringBillRecurrenceChangedEvent(id, getNextVersion(), recurrence));
    }

    public void changeType(RecurringBillType type) {
        applyChange(new RecurringBillTypeChangedEvent(id, getNextVersion(), type));
    }

    public void changeMaxPeriods(Long maxPeriods) {
        applyChange(new RecurringBillMaxPeriodsChangedEvent(id, getNextVersion(), maxPeriods));
    }

    public void changeWallet(UUID walletId) {
        applyChange(new RecurringBillWalletChangedEvent(id, getNextVersion(), walletId));
    }

    public void changeCategory(UUID categoryId) {
        applyChange(new RecurringBillCategoryChangedEvent(id, getNextVersion(), categoryId));
    }

    private void nextPeriod(long n) {
        if(currentPeriods.equals(maxPeriods)) {
            throw new RecurringBillReachedMaxPeriodsException("Recurring bill %s reached max periods of %s.".formatted(id, maxPeriods.getValue()));
        }

        if((currentPeriods.getValue() + n) > maxPeriods.getValue()) {
            applyChange(new RecurringBillPeriodExecutedEvent(id, getNextVersion(), maxPeriods.getValue()));
        }

        applyChange(new RecurringBillPeriodExecutedEvent(id, getNextVersion(), currentPeriods.getValue() + n));
    }

    private void reset() {
        applyChange(new RecurringBillResetEvent(id, getNextVersion()));
    }

    public void delete() {
        if(isDeleted)
            throw new RecurringBillAlreadyDeletedException("Recurring bill %s already deleted.".formatted(id));

        applyChange(new RecurringBillDeletedEvent(id, getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCreatedEvent event) {
        this.id = event.getAggregateId();
        this.name = RecurringBillName.create(event.getName());
        this.description = RecurringBillDescription.create(event.getDescription());
        this.amount = Currency.create(event.getAmount(), event.getCurrencyCode());
        this.type = event.getType();
        this.recurrence = RecurringBillRecurrence.create(event.getRecurrence());
        this.maxPeriods = RecurringBillPeriod.create(event.getMaxPeriods());
        this.currentPeriods = RecurringBillPeriod.create(0L);
        this.lastPeriod = null;
        this.walletId = event.getWalletId();
        this.categoryId = event.getCategoryId();
        this.isDeleted = false;
        this.createdAt = Instant.now();
        this.updatedAt = null;
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillRenamedEvent event) {
        this.name = RecurringBillName.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillDescriptionChangedEvent event) {
        this.description = RecurringBillDescription.create(event.getDescription());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillAmountUpdatedEvent event) {
        this.amount = Currency.create(event.getAmount(), amount.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCurrencyCodeChangedEvent event) {
        this.amount = Currency.create(amount.getValue(), event.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillRecurrenceChangedEvent event) {
        this.recurrence = RecurringBillRecurrence.create(event.getRecurrence());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillMaxPeriodsChangedEvent event) {
        this.maxPeriods = RecurringBillPeriod.create(event.getMaxPeriods());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillTypeChangedEvent event) {
        this.type = event.getType();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillWalletChangedEvent event) {
        this.walletId = event.getWalletId();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCategoryChangedEvent event) {
        this.categoryId = event.getCategoryId();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillPeriodExecutedEvent event) {
        this.currentPeriods = RecurringBillPeriod.create(event.getPeriods());
        this.lastPeriod = LocalDate.now();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillResetEvent event) {
        this.currentPeriods = RecurringBillPeriod.create(0L);
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public String getAggregateType() {
        return AggregateType.RECURRING_BILL.toString();
    }
}

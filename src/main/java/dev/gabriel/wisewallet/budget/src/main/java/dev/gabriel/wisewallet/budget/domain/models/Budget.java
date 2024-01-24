package dev.gabriel.wisewallet.budget.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.budget.domain.events.*;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetAlreadyDeletedException;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetItemAlreadyPresentException;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetItemNotPresentException;
import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetReachedMaxItemsException;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.currency.domain.models.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Budget extends Aggregate {
    private BudgetName name;
    private BudgetDescription description;
    private Currency amount;
    private List<BudgetItem> items;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    @JsonCreator
    private Budget(UUID id, Long version) {
        super(id, version);
    }

    private Budget(UUID id,
                   String name,
                   String description,
                   String currencyCode,
                   UUID userId
    ) {
        this(id, 0L);

        applyChange(new BudgetCreatedEvent(
                id,
                getNextVersion(),
                name,
                description,
                currencyCode,
                userId
        ));
    }

    public static Budget create(UUID id, String name, String description, String currencyCode, UUID userId) {
        return new Budget(id, name, description, currencyCode, userId);
    }

    public void rename(String name) {
        BudgetName.validate(name);

        applyChange(new BudgetRenamedEvent(id, getNextVersion(), name));
    }

    public void changeDescription(String description) {
        BudgetDescription.validate(description);

        applyChange(new BudgetDescriptionChangedEvent(id, getNextVersion(), description));
    }

    public void updateAmount(BigDecimal amount) {
        applyChange(new BudgetAmountUpdatedEvent(id, getNextVersion(), amount));
    }

    public void changeCurrencyCode(String currencyCode) {
        applyChange(new BudgetCurrencyCodeChangedEvent(id, getNextVersion(), currencyCode));
    }

    public void addItem(UUID billId, BigDecimal amount) {
        boolean isPresent = items.stream().anyMatch(i -> i.getBillId().equals(billId));

        if(items.size() == 16)
            throw new BudgetReachedMaxItemsException("Budget %s reached max items.".formatted(id));

        if(isPresent)
            throw new BudgetItemAlreadyPresentException("Bill %s already present in budget %s.".formatted(billId, id));

        applyChange(new BudgetItemAddedEvent(id, getNextVersion(), billId, amount));
    }

    public void removeItem(UUID billId, BigDecimal amount) {
        boolean isPresent = items.stream().anyMatch(i -> i.getBillId().equals(billId));

        if(!isPresent)
            throw new BudgetItemNotPresentException("Bill %s not present in budget %s.".formatted(billId, id));

        applyChange(new BudgetItemRemovedEvent(id, getNextVersion(), billId, amount));
    }

    public void delete() {
        if(isDeleted)
            throw new BudgetAlreadyDeletedException("Budget %s already deleted.".formatted(id));

        applyChange(new BudgetDeletedEvent(id, getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(BudgetCreatedEvent event) {
        this.id = event.getAggregateId();
        this.name = BudgetName.create(event.getName());
        this.description = BudgetDescription.create(event.getDescription());
        this.amount = Currency.create(BigDecimal.ZERO, event.getCurrencyCode());
        this.items = new ArrayList<>();
        this.createdAt = Instant.now();
        this.updatedAt = null;
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(BudgetRenamedEvent event) {
        this.name = BudgetName.create(event.getName());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BudgetDescriptionChangedEvent event) {
        this.description = BudgetDescription.create(event.getDescription());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BudgetCurrencyCodeChangedEvent event) {
        this.amount = Currency.create(amount.getValue(), event.getCurrencyCode());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BudgetItemAddedEvent event) {
        this.items.add(BudgetItem.create(event.getBillId(), id));
        this.amount = Currency.create(amount.getValue().add(event.getAmount()), amount.getCurrencyCode());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BudgetItemRemovedEvent event) {
        this.items.remove(BudgetItem.create(event.getBillId(), id));
        this.amount = Currency.create(amount.getValue().subtract(event.getAmount()), amount.getCurrencyCode());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(BudgetDeletedEvent event) {
        this.isDeleted = true;
        this.updatedAt = Instant.now();
    }

    @Override
    public String getAggregateType() {
        return AggregateType.BUDGET.toString();
    }
}

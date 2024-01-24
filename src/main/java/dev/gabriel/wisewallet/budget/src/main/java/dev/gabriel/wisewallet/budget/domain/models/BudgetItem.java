package dev.gabriel.wisewallet.budget.domain.models;

import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetValidationException;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BudgetItem extends ValueObject {
    private final UUID billId;
    private final UUID budgetId;

    public static BudgetItem create(UUID billId, UUID budgetId) {
        return new BudgetItem(billId, budgetId);
    }

    public static void validate(UUID billId, UUID budgetId) {
        if(billId == null || budgetId == null) {
            throw new BudgetValidationException("Budget validation failed on item field: an item must have a bill id and a budget id.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {billId, budgetId});
    }
}

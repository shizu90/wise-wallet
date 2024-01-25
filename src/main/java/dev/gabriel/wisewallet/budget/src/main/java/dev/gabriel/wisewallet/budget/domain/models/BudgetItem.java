package dev.gabriel.wisewallet.budget.domain.models;

import dev.gabriel.wisewallet.budget.domain.exceptions.BudgetValidationException;
import dev.gabriel.wisewallet.core.domain.models.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BudgetItem extends ValueObject {
    private final UUID billId;
    private final String name;
    private final BigDecimal amount;
    private final String currencyCode;
    private final String type;

    public static BudgetItem create(UUID billId, String name, BigDecimal amount, String currencyCode, String type) {
        return new BudgetItem(billId, name, amount, currencyCode, type);
    }

    public static void validate(UUID billId, String name, String currencyCode, BigDecimal amount, String type) {
        if(billId == null || name == null || amount == null || currencyCode == null || type == null) {
            throw new BudgetValidationException("Budget validation failed on item field: not a valid item.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {billId});
    }
}

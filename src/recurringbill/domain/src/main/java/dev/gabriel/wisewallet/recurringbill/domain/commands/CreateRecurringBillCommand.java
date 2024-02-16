package dev.gabriel.wisewallet.recurringbill.domain.commands;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBillType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CreateRecurringBillCommand extends RecurringBillCommand {
    private final String name;
    private final String description;
    private final BigDecimal amount;
    private final String currencyCode;
    private final Long recurrence;
    private final Long maxPeriods;
    private final RecurringBillType type;
    private final UUID walletId;
    private final UUID categoryId;

    public CreateRecurringBillCommand(UUID id,
                                      String name,
                                      String description,
                                      BigDecimal amount,
                                      String currencyCode,
                                      Long recurrence,
                                      Long maxPeriods,
                                      RecurringBillType type,
                                      UUID walletId,
                                      UUID categoryId
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.recurrence = recurrence;
        this.maxPeriods = maxPeriods;
        this.type = type;
        this.walletId = walletId;
        this.categoryId = categoryId;
    }
}

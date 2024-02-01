package dev.gabriel.wisewallet.recurringbill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeRecurringBillMaxPeriodsCommand extends RecurringBillCommand {
    private final Long maxPeriods;

    public ChangeRecurringBillMaxPeriodsCommand(UUID id, Long maxPeriods) {
        super(id);
        this.maxPeriods = maxPeriods;
    }
}

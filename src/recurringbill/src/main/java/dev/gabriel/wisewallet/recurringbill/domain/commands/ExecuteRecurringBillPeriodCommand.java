package dev.gabriel.wisewallet.recurringbill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ExecuteRecurringBillPeriodCommand extends RecurringBillCommand {
    private final Long numberOfPeriods;

    public ExecuteRecurringBillPeriodCommand(UUID id, Long numberOfPeriods) {
        super(id);
        this.numberOfPeriods = numberOfPeriods;
    }
}

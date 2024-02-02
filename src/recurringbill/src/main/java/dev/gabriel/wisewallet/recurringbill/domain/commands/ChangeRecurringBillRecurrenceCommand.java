package dev.gabriel.wisewallet.recurringbill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeRecurringBillRecurrenceCommand extends RecurringBillCommand {
    private final Long recurrence;

    public ChangeRecurringBillRecurrenceCommand(UUID id, Long recurrence) {
        super(id);
        this.recurrence = recurrence;
    }
}

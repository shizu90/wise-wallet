package dev.gabriel.wisewallet.recurringbill.domain.commands;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBillType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeRecurringBillTypeCommand extends RecurringBillCommand {
    private final RecurringBillType type;

    public ChangeRecurringBillTypeCommand(UUID id, RecurringBillType type) {
        super(id);
        this.type = type;
    }
}

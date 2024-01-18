package dev.gabriel.wisewallet.bill.domain.commands;

import dev.gabriel.wisewallet.bill.domain.models.BillType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeBillTypeCommand extends BillCommand {
    private final BillType type;

    public ChangeBillTypeCommand(UUID id, BillType type) {
        super(id);
        this.type = type;
    }
}

package dev.gabriel.wisewallet.bill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeBillDescriptionCommand extends BillCommand {
    private final String description;

    public ChangeBillDescriptionCommand(UUID id, String description) {
        super(id);
        this.description = description;
    }
}

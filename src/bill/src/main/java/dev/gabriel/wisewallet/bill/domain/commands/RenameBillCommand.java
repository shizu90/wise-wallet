package dev.gabriel.wisewallet.bill.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RenameBillCommand extends BillCommand {
    private final String name;

    public RenameBillCommand(UUID id, String name) {
        super(id);
        this.name = name;
    }
}

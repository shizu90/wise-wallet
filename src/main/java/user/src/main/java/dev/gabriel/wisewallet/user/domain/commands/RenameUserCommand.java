package dev.gabriel.wisewallet.user.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RenameUserCommand extends UserCommand {
    private final String name;

    public RenameUserCommand(UUID aggregateId, String name) {
        super(aggregateId);
        this.name = name;
    }
}

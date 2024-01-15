package dev.gabriel.wisewallet.user.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeUserEmailCommand extends UserCommand {
    private final String email;

    public ChangeUserEmailCommand(UUID aggregateId, String email) {
        super(aggregateId);
        this.email = email;
    }
}

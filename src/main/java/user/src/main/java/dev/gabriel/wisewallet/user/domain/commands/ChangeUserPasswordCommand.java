package dev.gabriel.wisewallet.user.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeUserPasswordCommand extends UserCommand {
    private final String password;

    public ChangeUserPasswordCommand(UUID aggregateId, String password) {
        super(aggregateId);
        this.password = password;
    }
}

package dev.gabriel.wisewallet.user.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteUserCommand extends UserCommand {
    public DeleteUserCommand(UUID aggregateId) {
        super(aggregateId);
    }
}

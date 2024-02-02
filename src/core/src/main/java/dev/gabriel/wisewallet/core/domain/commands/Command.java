package dev.gabriel.wisewallet.core.domain.commands;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Command {
    protected final UUID aggregateId;
    protected final String aggregateType;
}

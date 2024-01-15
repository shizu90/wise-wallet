package dev.gabriel.wisewallet.core.domain.events;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public abstract class DomainEvent {
    protected final UUID aggregateId;
    protected final Long version;
    protected final Instant timestamp = Instant.now();

    @NonNull
    public abstract String getEventType();
}

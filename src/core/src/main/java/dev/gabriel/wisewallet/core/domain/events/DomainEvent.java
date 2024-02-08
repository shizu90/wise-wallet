package dev.gabriel.wisewallet.core.domain.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class DomainEvent {
    protected final UUID aggregateId;
    protected final Long version;
    protected final Instant timestamp;

    protected DomainEvent(UUID aggregateId, Long version) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.timestamp = Instant.now();
    }

    @NonNull
    @JsonIgnore
    public abstract String getEventType();
}

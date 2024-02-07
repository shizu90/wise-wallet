package dev.gabriel.wisewallet.core.domain.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;

import java.time.*;
import java.util.UUID;

@Getter
public abstract class DomainEvent {
    protected final UUID aggregateId;
    protected final Long version;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    protected final ZonedDateTime timestamp;

    protected DomainEvent(UUID aggregateId, Long version) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.timestamp = ZonedDateTime.now();
    }

    @NonNull
    @JsonIgnore
    public abstract String getEventType();
}

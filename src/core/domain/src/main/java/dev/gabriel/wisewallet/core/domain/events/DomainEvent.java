package dev.gabriel.wisewallet.core.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Getter
public abstract class DomainEvent {
    protected final UUID aggregateId;
    protected final Long version;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
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

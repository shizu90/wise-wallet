package dev.gabriel.wisewallet.core.infrastructure.eventstore.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.events.DomainEventWithId;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers.EventTypeMapper;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final EventTypeMapper eventTypeMapper;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public <T extends DomainEvent> DomainEventWithId<T> append(@NonNull DomainEvent event) {
        List<DomainEventWithId<T>> result = jdbcTemplate.query("""
                    INSERT INTO events (transaction_id, aggregate_id, version, type, payload)
                    VALUES(pg_current_xact_id(), :aggregateId, :version, :type, :payload::json)
                    RETURNING id, type, payload, transaction_id
                """,
                Map.of(
                        "aggregateId", event.getAggregateId(),
                        "version", event.getVersion(),
                        "type", event.getEventType(),
                        "payload", objectMapper.writeValueAsString(event)
                ),
                this::toEvent
        );

        return result.get(0);
    }

    public List<DomainEventWithId<DomainEvent>> getEvents(@NonNull UUID aggregateId, @Nullable Long fromVersion, @Nullable Long toVersion) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("aggregateId", aggregateId);
        parameters.addValue("fromVersion", fromVersion, Types.INTEGER);
        parameters.addValue("toVersion", toVersion, Types.INTEGER);

        return jdbcTemplate.query("""
                    SELECT id, transaction_id::text, type, payload
                    FROM events WHERE aggregate_id = :aggregateId
                    AND (:fromVersion IS NULL OR version > :fromVersion)
                    AND (:toVersion IS NULL OR version <= :toVersion)
                    ORDER BY version ASC
                """,
                parameters,
                this::toEvent);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private <T extends DomainEvent> DomainEventWithId<T> toEvent(ResultSet resultSet, int rowNum) {
        Long id = resultSet.getLong("id");
        String transactionId = resultSet.getString("transaction_id");
        String eventType = resultSet.getString("type");
        PGobject jsonObj = (PGobject) resultSet.getObject("payload");
        String json = jsonObj.getValue();
        Class<? extends DomainEvent> eventClass = eventTypeMapper.getClassByEventType(eventType);
        DomainEvent domainEvent = objectMapper.readValue(json, eventClass);
        return new DomainEventWithId<>(id, new BigInteger(transactionId), (T) domainEvent);
    }
}

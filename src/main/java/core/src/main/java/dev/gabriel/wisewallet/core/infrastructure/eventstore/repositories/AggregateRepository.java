package dev.gabriel.wisewallet.core.infrastructure.eventstore.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.core.infrastructure.eventstore.mappers.AggregateTypeMapper;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AggregateRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AggregateTypeMapper aggregateTypeMapper;
    private final ObjectMapper objectMapper;

    public void createIfNotExists(@NonNull String aggregateType, @NonNull UUID aggregateId) {
        jdbcTemplate.update("""
                INSERT INTO aggregates (id, version, type)
                VALUES (:aggregateId, 0, :aggregateType)
                ON CONFLICT DO NOTHING
                """,
                Map.of("aggregateId", aggregateId, "aggregateType", aggregateType)
        );
    }

    public boolean checkAndUpdateVersion(@NonNull UUID aggregateId, Long expectedVersion, Long newVersion) {
        int updatedRows = jdbcTemplate.update("""
                UPDATE aggregates SET version = :newVersion WHERE id = :aggregateId AND version = :expectedVersion
                """,
                Map.of("newVersion", newVersion,
                        "aggregateId", aggregateId,
                        "expectedVersion", expectedVersion
                ));
        return updatedRows > 0;
    }

    @SneakyThrows
    public void createSnapshot(@NonNull Aggregate aggregate) {
        jdbcTemplate.update("""
                INSERT INTO snapshots (aggregate_id, version, data)
                VALUES (:aggregateId, :version, :data::json)
                """,
                Map.of(
                        "aggregateId", aggregate.getId().getValue(),
                        "version", aggregate.getVersion(),
                        "data", objectMapper.writeValueAsString(aggregate)
                ));
    }

    @SuppressWarnings("ConstantConditions")
    public Optional<Aggregate> getSnapshot(@NonNull UUID aggregateId, @Nullable Long version) {
        return jdbcTemplate.query("""
                SELECT aggregate.type, snapshot.data
                FROM snapshots snapshot
                JOIN aggregates aggregate ON aggregate.id = snapshot.aggregate_id
                WHERE snapshot.aggregate_id = :aggregateId
                AND (:version IS NULL OR snapshot.version <= :version)
                ORDER BY snapshot.version DESC
                LIMIT 1
                """,
                Map.of("aggregateId", aggregateId, "version", version),
                this::toAggregate
        ).stream().findFirst();
    }

    @SneakyThrows
    private Aggregate toAggregate(ResultSet resultSet, int rowNum) {
        String aggregateType = resultSet.getString("type");
        PGobject jsonObj = (PGobject) resultSet.getObject("data");
        String json = jsonObj.getValue();
        Class<? extends Aggregate> aggregateClass = aggregateTypeMapper.getClassByAggregateType(aggregateType);
        return objectMapper.readValue(json, aggregateClass);
    }
}

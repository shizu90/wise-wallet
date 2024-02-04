package dev.gabriel.wisewallet.reminder.infrastructure.projection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReminderProjectionRepository extends MongoRepository<ReminderProjection, UUID> {
    @Query(value = "{$and: [{'id': ?0}, {'isDeleted': false}]}")
    Optional<ReminderProjection> find(UUID id);

    @Query(value = "{$or: [{'userId': ?0}, {'isDeleted': false}, {'name': {$regex: ?1}}]}", sort = "{'name': 1}")
    Page<ReminderProjection> findByUserIdAndName(UUID userId, String name, Pageable pageable);

    @Query(value = "{$and: [{'userId': ?0}, {'name': ?1}, {'isDeleted': false}]}")
    List<ReminderProjection> findByUserIdAndName(UUID userId, String name);

    @Query(value = "{'started': true}")
    List<ReminderProjection> findStartedReminders();
}

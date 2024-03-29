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

    @Query(value = """
        {$or: [
            {'userId': ?0, 'name': {$regex: ?1}, 'isDeleted': false},
            {'userId': ?0, 'isDeleted': false}
        ]}
    """, sort = "{'name': 1}")
    Page<ReminderProjection> find(UUID userId, String name, Pageable pageable);

    @Query(value = "{$and: [{'userId': ?0}, {'name': ?1}, {'isDeleted': false}]}")
    List<ReminderProjection> findByUserIdAndName(UUID userId, String name);

    @Query(value = "{$and: [{'started': true}, {'isDeleted': false}]}")
    List<ReminderProjection> findStartedReminders();

    @Query(value = "{$and: [{'userId': ?0}, {'isDeleted': false}]}")
    List<ReminderProjection> findByUserId(UUID userId);
}

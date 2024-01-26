package dev.gabriel.wisewallet.budget.infrastructure.projection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BudgetProjectionRepository extends MongoRepository<BudgetProjection, UUID> {
    @Query(value = "{$and: [{'id': ?0}, {'isDeleted': false}]}")
    Optional<BudgetProjection> find(UUID id);

    @Query(value = "{$and: [{'userId': ?0}, {'isDeleted': false}]}")
    Page<BudgetProjection> findByUserId(UUID userId, Pageable pageable);

    @Query(value = "{$and: [{'userId': ?0}, {'name': {$regex: ?1}}, {'isDeleted': false}]}")
    Page<BudgetProjection> findByUserIdAndName(UUID userId, String name, Pageable pageable);

    @Query(value = "{$and: {'userId': ?0}, {'name': ?1}, {'isDeleted': false}}")
    List<BudgetProjection> findByName(UUID userId, String name);
}
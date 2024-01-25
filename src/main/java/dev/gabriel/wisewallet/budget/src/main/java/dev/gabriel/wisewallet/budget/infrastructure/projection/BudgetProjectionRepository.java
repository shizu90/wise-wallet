package dev.gabriel.wisewallet.budget.infrastructure.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BudgetProjectionRepository extends MongoRepository<BudgetProjection, UUID> {
    @Query(value = "{$and: {'id': ?0, 'isDeleted': false}}")
    Optional<BudgetProjection> find(UUID id);
}

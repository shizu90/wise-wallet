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

    @Query(value = """
        {$or: [
            {'userId': ?0, 'isDeleted': false, 'name': {$regex: ?1}},
            {'userId': ?0, 'isDeleted': false}
        ]}
    """, sort = "{'name': 1}")
    Page<BudgetProjection> find(UUID userId, String name, Pageable pageable);

    @Query(value = "{$or: [{'userId': ?0}, {'name': {$regex: ?1}}, {'isDeleted': false}]}")
    Page<BudgetProjection> findByUserIdAndName(UUID userId, String name, Pageable pageable);

    @Query(value = "{$and: [{'userId': ?1}, {'name': ?0}, {'isDeleted': false}]}")
    List<BudgetProjection> findByNameAndUserId(String name, UUID userId);
}

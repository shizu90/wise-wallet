package dev.gabriel.wisewallet.category.infrastructure.projection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryProjectionRepository extends MongoRepository<CategoryProjection, UUID> {
    @Query(value = "{$and: [{'id': ?0}, {'isDeleted': false}]}")
    Optional<CategoryProjection> find(UUID id);

    @Query(value = "{$and: [{$or: [{'userId': ?0}, {'userId': null}]}, {'isDeleted': false}]}")
    Page<CategoryProjection> findByUserId(UUID userId, Pageable pageable);

    @Query(value = "{$and: [{$or: [{'userId': ?0}, {'userId': null}]}, {'name': {$regex: ?1}}]}")
    Page<CategoryProjection> findByUserIdAndName(UUID userId, String name, Pageable pageable);

    @Query(value = "{$and: [{$or: [{'userId': ?0}, {'userId': null}]}, {'name': ?1}]}")
    List<CategoryProjection> findByUserIdAndName(UUID userId, String name);
}

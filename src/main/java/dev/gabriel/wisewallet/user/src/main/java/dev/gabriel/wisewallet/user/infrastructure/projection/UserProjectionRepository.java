package dev.gabriel.wisewallet.user.infrastructure.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProjectionRepository extends MongoRepository<UserProjection, UUID> {
    @Query(value = "{$and: [{'id': ?0}, {'isDeleted': false}]}")
    Optional<UserProjection> find(UUID userId);

    List<UserProjection> findByEmail(String email);
}

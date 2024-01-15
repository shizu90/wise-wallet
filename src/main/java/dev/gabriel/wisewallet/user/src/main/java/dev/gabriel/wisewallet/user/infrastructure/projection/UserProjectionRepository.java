package dev.gabriel.wisewallet.user.infrastructure.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserProjectionRepository extends MongoRepository<UserProjection, UUID> {
    List<UserProjection> findByEmail(String email);
}

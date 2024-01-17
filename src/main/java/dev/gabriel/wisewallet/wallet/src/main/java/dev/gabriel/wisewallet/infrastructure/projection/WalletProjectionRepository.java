package dev.gabriel.wisewallet.infrastructure.projection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WalletProjectionRepository extends MongoRepository<WalletProjection, UUID> {
    List<WalletProjection> findByUserIdAndName(UUID userId, String name);
    Page<WalletProjection> findByUserId(UUID userId, Pageable pageable);
}

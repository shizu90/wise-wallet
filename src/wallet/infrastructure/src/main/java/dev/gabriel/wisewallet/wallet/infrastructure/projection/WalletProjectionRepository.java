package dev.gabriel.wisewallet.wallet.infrastructure.projection;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletProjectionRepository extends MongoRepository<WalletProjection, UUID> {
    @Query(value = "{$and: [{'id': ?0}, {'isDeleted': false}]}")
    Optional<WalletProjection> find(UUID walletId);

    @Query(value = """
        {$or: [
            {'userId': ?0, 'name': {$regex: ?1}, 'isDeleted': false},
            {'userId': ?0, 'type': ?2, 'isDeleted': false},
            {'userId': ?0, 'isDeleted': false}
        ]}
    """, sort = "{'name': 1}")
    Page<WalletProjection> find(UUID userId, String name, WalletType type, Pageable pageable);

    @Query(value = "{$and: [{'userId': ?0}, {'name': ?1}, {'isDeleted': false}]}")
    List<WalletProjection> findByUserIdAndName(UUID userId, String name);

    @Query(value = "{$and: [{'userId': ?0}, {'isDeleted': false}]}")
    List<WalletProjection> findByUserId(UUID userId);
}

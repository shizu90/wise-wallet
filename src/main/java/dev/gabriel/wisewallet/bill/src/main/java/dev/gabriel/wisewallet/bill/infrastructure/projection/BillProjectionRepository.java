package dev.gabriel.wisewallet.bill.infrastructure.projection;

import dev.gabriel.wisewallet.bill.domain.models.BillType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BillProjectionRepository extends MongoRepository<BillProjection, UUID> {

    @Query(value = "{$and: [{'id': ?0}, {'isDeleted': false}]}")
    Optional<BillProjection> find(UUID id);

    @Query(value = "{$and: [{'walletId': ?0}, {'isDeleted': false}]}", sort = "{'name': 1}")
    Page<BillProjection> findByWalletId(UUID walletId, Pageable pageable);

    @Query(value = "{$and: [{'walletId': ?0, 'isDeleted': false}, {$or: [{'name': {$regex: ?1}}, {'type': ?2}]}}", sort = "{'name': 1}")
    Page<BillProjection> findByWalletIdAndNameOrType(UUID walletId, String name, BillType type, Pageable pageable);

    @Query(value = "{$and: [{'walletId': ?0, 'isDeleted': false}, {$and: [{'name': {$regex: ?1}}, {'type': ?2}]}}", sort = "{'name': 1}")
    Page<BillProjection> findByWalletIdAndNameAndType(UUID walletId, String name, BillType type, Pageable pageable);
}

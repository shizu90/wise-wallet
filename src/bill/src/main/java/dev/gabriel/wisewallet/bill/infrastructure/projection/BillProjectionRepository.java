package dev.gabriel.wisewallet.bill.infrastructure.projection;

import dev.gabriel.wisewallet.bill.domain.models.BillType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BillProjectionRepository extends MongoRepository<BillProjection, UUID> {

    @Query(value = "{$and: [{'id': ?0}, {'isDeleted': false}]}")
    Optional<BillProjection> find(UUID id);

    @Query(value = "{$or: [{'walletId': ?0}, {'isDeleted': false}, {$or: [{'name': {$regex: ?1}}, {'type': ?2}, {'categoryId': ?3}]}]}", sort = "{'name': 1}")
    Page<BillProjection> find(UUID walletId, String name, BillType type, UUID categoryId, Pageable pageable);

    @Query(value = "{$and: [{'categoryId': ?0}]}")
    List<BillProjection> findByCategoryId(UUID categoryId);

    @Query(value = "{$and: [{'walletId': ?0}, {'isDeleted': false}]}")
    List<BillProjection> findByWalletId(UUID walletId);

    @Query(value = "{$and: [{'name': ?0}, {'walletId': ?1}, {'isDeleted': false}]}")
    List<BillProjection> findByNameAndWalletId(String name, UUID walletId);
}

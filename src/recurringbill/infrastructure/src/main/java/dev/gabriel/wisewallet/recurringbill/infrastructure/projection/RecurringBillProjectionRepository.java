package dev.gabriel.wisewallet.recurringbill.infrastructure.projection;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBillType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecurringBillProjectionRepository extends MongoRepository<RecurringBillProjection, UUID> {
    @Query(value = "{$and: [{'id': ?0}, {'isDeleted': false}]}")
    Optional<RecurringBillProjection> find(UUID id);

    @Query(value = """
        {$or: [
            {'walletId': ?0, 'name': {$regex: ?1}, 'isDeleted': false},
            {'walletId': ?0, 'type': ?2, 'isDeleted': false},
            {'walletId': ?0, 'categoryId': ?3, 'isDeleted': false},
            {'walletId': ?0, 'isDeleted': false}
        ]}
    """, sort = "{'name': 1}")
    Page<RecurringBillProjection> find(UUID walletId, String name, RecurringBillType type, UUID categoryId, Pageable pageable);

    @Query(value = "{$and: [{'name': ?0}, {'walletId': ?1}, {'isDeleted': false}]}")
    List<RecurringBillProjection> findByNameAndWalletId(String name, UUID walletId);

    @Query(value = "{$and: [{'categoryId': ?0}]}")
    List<RecurringBillProjection> findByCategoryId(UUID categoryId);

    @Query(value = "{$and: [{'walletId': ?0}, {'isDeleted': false}]}")
    List<RecurringBillProjection> findByWalletId(UUID walletId);
}

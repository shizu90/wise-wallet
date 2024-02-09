package dev.gabriel.wisewallet.recurringbill.infrastructure.projection;

import dev.gabriel.wisewallet.recurringbill.domain.models.RecurringBillType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "RecurringBills")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class RecurringBillProjection {
    @Id
    private UUID id;
    private String name;
    private String description;
    private RecurringBillType type;
    private BigDecimal amount;
    private String currencyCode;
    private Long recurrence;
    private Long currentPeriods;
    private Long maxPeriods;
    private LocalDate lastPeriod;
    private UUID walletId;
    private UUID categoryId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    public static RecurringBillProjection create(UUID id,
                                                 String name,
                                                 String description,
                                                 RecurringBillType type,
                                                 BigDecimal amount,
                                                 String currencyCode,
                                                 Long recurrence,
                                                 Long currentPeriods,
                                                 Long maxPeriods,
                                                 LocalDate lastPeriod,
                                                 UUID walletId,
                                                 UUID categoryId,
                                                 Instant createdAt,
                                                 Instant updatedAt,
                                                 Boolean isDeleted
    ) {
        return new RecurringBillProjection(
                id,
                name,
                description,
                type,
                amount,
                currencyCode,
                recurrence,
                currentPeriods,
                maxPeriods,
                lastPeriod,
                walletId,
                categoryId,
                createdAt,
                updatedAt,
                isDeleted
        );
    }
}

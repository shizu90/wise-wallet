package dev.gabriel.wisewallet.budget.infrastructure.projection;

import dev.gabriel.wisewallet.budget.domain.models.BudgetItem;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "Budgets")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class BudgetProjection {
    @Id
    private UUID id;
    private String name;
    private String description;
    private BigDecimal amount;
    private String currencyCode;
    private List<BudgetItem> items;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    public static BudgetProjection create(UUID id,
                                          String name,
                                          String description,
                                          BigDecimal amount,
                                          String currencyCode,
                                          List<BudgetItem> items,
                                          UUID userId,
                                          Instant createdAt,
                                          Instant updatedAt,
                                          Boolean isDeleted
    ) {
        return new BudgetProjection(
                id,
                name,
                description,
                amount,
                currencyCode,
                items,
                userId,
                createdAt,
                updatedAt,
                isDeleted
        );
    }
}

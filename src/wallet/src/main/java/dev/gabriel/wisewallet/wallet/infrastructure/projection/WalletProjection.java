package dev.gabriel.wisewallet.wallet.infrastructure.projection;

import dev.gabriel.wisewallet.wallet.domain.models.WalletType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "Wallets")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class WalletProjection {
    @Id
    private UUID id;
    private String name;
    private String description;
    private BigDecimal balance;
    private BigDecimal initialBalance;
    private String currencyCode;
    private WalletType type;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    public static WalletProjection create(UUID id,
                                          String name,
                                          String description,
                                          BigDecimal balance,
                                          BigDecimal initialBalance,
                                          String currencyCode,
                                          WalletType type,
                                          UUID userId,
                                          Instant createdAt,
                                          Instant updatedAt,
                                          Boolean isDeleted
    ) {
        return new WalletProjection(
                id,
                name,
                description,
                balance,
                initialBalance,
                currencyCode,
                type,
                userId,
                createdAt,
                updatedAt,
                isDeleted
        );
    }
}

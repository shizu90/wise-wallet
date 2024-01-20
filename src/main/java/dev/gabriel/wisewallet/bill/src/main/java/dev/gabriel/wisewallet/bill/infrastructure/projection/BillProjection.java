package dev.gabriel.wisewallet.bill.infrastructure.projection;

import dev.gabriel.wisewallet.bill.domain.models.BillType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document(collection = "Bills")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class BillProjection {
    @Id
    private UUID id;
    private String name;
    private String description;
    private BigDecimal amount;
    private String currencyCode;
    private BillType type;
    private UUID walletId;
    private UUID categoryId;
    private Boolean isDeleted;

    public static BillProjection create(UUID id,
                                        String name,
                                        String description,
                                        BigDecimal amount,
                                        String currencyCode,
                                        BillType type,
                                        UUID walletId,
                                        UUID categoryId,
                                        Boolean isDeleted
    ) {
        return new BillProjection(id, name, description, amount, currencyCode, type, walletId, categoryId, isDeleted);
    }
}

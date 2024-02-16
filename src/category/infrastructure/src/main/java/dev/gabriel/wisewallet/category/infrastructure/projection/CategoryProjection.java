package dev.gabriel.wisewallet.category.infrastructure.projection;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "Categories")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class CategoryProjection {
    @Id
    private UUID id;
    private String name;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    public static CategoryProjection create(UUID id,
                                            String name,
                                            UUID userId,
                                            Instant createdAt,
                                            Instant updatedAt,
                                            Boolean isDeleted
    ) {
        return new CategoryProjection(id, name, userId, createdAt, updatedAt, isDeleted);
    }
}

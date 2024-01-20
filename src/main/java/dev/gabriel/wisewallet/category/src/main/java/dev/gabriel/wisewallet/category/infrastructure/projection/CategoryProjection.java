package dev.gabriel.wisewallet.category.infrastructure.projection;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Categories")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class CategoryProjection {
    @Id
    private UUID id;
    private String name;
    private UUID userId;
    private Boolean isDeleted;

    public static CategoryProjection create(UUID id, String name, UUID userId, Boolean isDeleted) {
        return new CategoryProjection(id, name, userId, isDeleted);
    }
}

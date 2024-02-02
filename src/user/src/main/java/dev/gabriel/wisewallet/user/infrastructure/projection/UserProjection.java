package dev.gabriel.wisewallet.user.infrastructure.projection;

import dev.gabriel.wisewallet.user.domain.models.UserConfiguration;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "Users")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class UserProjection {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserConfiguration configuration;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    public static UserProjection create(UUID id,
                                        String name,
                                        String email,
                                        String password,
                                        UserConfiguration configuration,
                                        Instant createdAt,
                                        Instant updatedAt,
                                        Boolean isDeleted
    ) {
        return new UserProjection(id, name, email, password, configuration, createdAt, updatedAt, isDeleted);
    }
}

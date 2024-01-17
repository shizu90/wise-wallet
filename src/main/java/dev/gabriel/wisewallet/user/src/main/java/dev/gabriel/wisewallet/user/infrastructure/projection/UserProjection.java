package dev.gabriel.wisewallet.user.infrastructure.projection;

import dev.gabriel.wisewallet.user.domain.models.UserConfiguration;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Boolean isDeleted;

    public static UserProjection create(UUID id,
                                        String name,
                                        String email,
                                        String password,
                                        UserConfiguration configuration,
                                        Boolean isDeleted
    ) {
        return new UserProjection(id, name, email, password, configuration, isDeleted);
    }
}

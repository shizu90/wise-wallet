package dev.gabriel.wisewallet.user.infrastructure.mappers;

import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjection;
import org.springframework.stereotype.Component;

@Component
public class UserProjectionMapper {
    public UserProjection toProjection(User user) {
        return UserProjection.create(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getConfiguration()
        );
    }
}

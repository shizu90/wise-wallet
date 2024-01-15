package dev.gabriel.wisewallet.user.presentation.dtos;

import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjection;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    public UserResponseDto toDto(User user) {
        if(user == null) return null;
        return new UserResponseDto(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getConfiguration()
        );
    }

    public UserResponseDto toDto(UserProjection userProjection) {
        if(userProjection == null) return null;
        return new UserResponseDto(
                userProjection.getId(),
                userProjection.getName(),
                userProjection.getEmail(),
                userProjection.getConfiguration()
        );
    }
}

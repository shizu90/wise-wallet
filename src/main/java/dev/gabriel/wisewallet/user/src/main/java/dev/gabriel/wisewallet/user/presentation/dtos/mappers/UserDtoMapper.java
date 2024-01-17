package dev.gabriel.wisewallet.user.presentation.dtos.mappers;

import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjection;
import dev.gabriel.wisewallet.user.presentation.dtos.UserConfigurationDto;
import dev.gabriel.wisewallet.user.presentation.dtos.UserResponseDto;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserResponseDto toResponseDto(@Nullable User user) {
        if(user == null) return null;
        return new UserResponseDto(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                new UserConfigurationDto(
                        user.getConfiguration().getDefaultCurrencyCode(),
                        user.getConfiguration().getDefaultLanguage()
                )
        );
    }

    public UserResponseDto toResponseDto(@Nullable UserProjection userProjection) {
        if(userProjection == null) return null;
        return new UserResponseDto(
                userProjection.getId(),
                userProjection.getName(),
                userProjection.getEmail(),
                new UserConfigurationDto(
                        userProjection.getConfiguration().getDefaultCurrencyCode(),
                        userProjection.getConfiguration().getDefaultLanguage()
                )
        );
    }
}

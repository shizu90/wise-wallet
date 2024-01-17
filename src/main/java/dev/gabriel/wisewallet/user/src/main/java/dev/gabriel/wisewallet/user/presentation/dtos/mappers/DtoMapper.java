package dev.gabriel.wisewallet.user.presentation.dtos.mappers;

import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.models.Username;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjection;
import dev.gabriel.wisewallet.user.presentation.dtos.UserConfigurationDto;
import dev.gabriel.wisewallet.user.presentation.dtos.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface DtoMapper {
    @SuppressWarnings("unused")
    UserConfigurationDto userConfigurationToUserConfigurationDto();

    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "email.value", target = "email")
    UserResponseDto userToUserResponseDto(User user);

    UserResponseDto userProjectionToUserResponseDto(UserProjection userProjection);
}

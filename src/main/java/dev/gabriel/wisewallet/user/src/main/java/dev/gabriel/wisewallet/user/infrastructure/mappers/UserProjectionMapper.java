package dev.gabriel.wisewallet.user.infrastructure.mappers;

import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserProjectionMapper {

    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "password.value", target = "password")
    UserProjection userToUserProjection(User user);
}

package dev.gabriel.wisewallet.user.infrastructure.services;

import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjectionRepository;
import dev.gabriel.wisewallet.user.presentation.dtos.UserRequestDto;
import dev.gabriel.wisewallet.user.presentation.dtos.UserResponseDto;
import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.user.domain.commands.*;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.presentation.dtos.mappers.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final CommandBus<UserCommand> commandBus;
    private final UserProjectionRepository userProjectionRepository;
    private final UserDtoMapper dtoMapper;

    public UserResponseDto getUser(UUID id) {
        return dtoMapper.toResponseDto(userProjectionRepository.find(id).orElse(null));
    }

    public UserResponseDto newUser(UserRequestDto request) {
        User user = (User) commandBus.execute(
                new CreateUserCommand(
                        UUID.randomUUID(),
                        request.name(),
                        request.email(),
                        request.password(),
                        request.defaultCurrencyCode(),
                        request.defaultLanguage()
                ));

        return dtoMapper.toResponseDto(user);
    }

    public UserResponseDto updateUserData(UserRequestDto request) {
        User user = null;

        if(!(request.name() == null || request.name().isBlank())) {
            user = (User) commandBus.execute(
                    new RenameUserCommand(
                            request.id(),
                            request.name()
                    ));
        }
        if(!(request.email() == null || request.email().isBlank())) {
            user = (User) commandBus.execute(
                    new ChangeUserEmailCommand(
                            request.id(),
                            request.email()
                    ));
        }
        if(!(request.password() == null || request.password().isBlank())) {
            user = (User) commandBus.execute(
                    new ChangeUserPasswordCommand(
                            request.id(),
                            request.password()
                    )
            );
        }

        if(!(request.defaultLanguage() == null || request.defaultLanguage().isBlank()) ||
                !(request.defaultCurrencyCode() == null || request.defaultCurrencyCode().isBlank())) {
            user = (User) commandBus.execute(
                    new ChangeUserConfigurationCommand(
                            request.id(),
                            request.defaultCurrencyCode(),
                            request.defaultLanguage()
                    )
            );
        }
        return dtoMapper.toResponseDto(user);
    }

    public void deleteUser(UUID id) {
        commandBus.execute(new DeleteUserCommand(id));
    }
}

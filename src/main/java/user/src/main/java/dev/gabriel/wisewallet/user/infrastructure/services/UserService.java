package dev.gabriel.wisewallet.user.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.user.domain.commands.*;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.models.UserConfiguration;
import dev.gabriel.wisewallet.user.infrastructure.projection.UserProjectionRepository;
import dev.gabriel.wisewallet.user.presentation.dtos.DtoMapper;
import dev.gabriel.wisewallet.user.presentation.dtos.UserRequestDto;
import dev.gabriel.wisewallet.user.presentation.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final CommandBus<UserCommand> commandBus;
    private final UserProjectionRepository userProjectionRepository;
    private final DtoMapper dtoMapper;

    public UserResponseDto getById(UUID id) {
        return dtoMapper.toDto(userProjectionRepository.findById(id).orElse(null));
    }

    public UserResponseDto create(UserRequestDto request) {
        User user = (User) commandBus.execute(
                new CreateUserCommand(
                        request.name(),
                        request.email(),
                        request.password(),
                        request.defaultCurrencyCode(),
                        request.defaultLanguage()
                ));

        return dtoMapper.toDto(user);
    }

    public UserResponseDto update(UserRequestDto request) {
        User user = null;
        if(!(request.name() == null || request.name().isEmpty() || request.name().isBlank())) {
            user = (User) commandBus.execute(
                    new RenameUserCommand(
                            request.id(),
                            request.name()
                    ));
        }
        if(!(request.email() == null || request.email().isEmpty() || request.email().isBlank())) {
            user = (User) commandBus.execute(
                    new ChangeUserEmailCommand(
                            request.id(),
                            request.email()
                    ));
        }
        if(!(request.password() == null || request.password().isEmpty() || request.password().isBlank())) {
            user = (User) commandBus.execute(
                    new ChangeUserPasswordCommand(
                            request.id(),
                            request.password()
                    )
            );
        }
        UserConfiguration configuration = UserConfiguration.create(request.defaultCurrencyCode(), request.defaultLanguage());
        if(!(configuration.isNull() || configuration.isBlank() || configuration.isEmpty())) {
            user = (User) commandBus.execute(
                    new ChangeUserConfigurationCommand(
                            request.id(),
                            configuration.getDefaultCurrencyCode(),
                            configuration.getDefaultLanguage()
                    )
            );
        }
        return dtoMapper.toDto(user);
    }

    public void delete(UUID id) {
        commandBus.execute(new DeleteUserCommand(id));
    }
}

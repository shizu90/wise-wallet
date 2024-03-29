package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.CreateUserCommand;
import dev.gabriel.wisewallet.user.domain.models.Password;
import dev.gabriel.wisewallet.user.domain.services.CheckUniqueEmail;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.user.domain.exceptions.UserAlreadyExistsException;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import dev.gabriel.wisewallet.user.domain.services.EncryptPassword;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {
    private final UserRepository userEventStore;
    private final CheckUniqueEmail checkUniqueEmail;
    private final EncryptPassword encryptPassword;

    @Override
    public User handle(@NonNull CreateUserCommand command) {
        if(checkUniqueEmail.exists(command.getEmail()))
            throw new UserAlreadyExistsException("User with email %s already exists.".formatted(command.getEmail()));

        Password.validate(command.getPassword());
        User user = User.create(
                command.getAggregateId(),
                command.getName(),
                command.getEmail(),
                encryptPassword.encrypt(command.getPassword()),
                command.getDefaultCurrencyCode(),
                command.getDefaultLanguage()
        );

        userEventStore.saveChanges(user);

        return user;
    }

    @Override
    @NonNull
    public Class<CreateUserCommand> getCommandType() {
        return CreateUserCommand.class;
    }
}

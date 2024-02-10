package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.user.domain.commands.ChangeUserPasswordCommand;
import dev.gabriel.wisewallet.user.domain.exceptions.UserNotFoundException;
import dev.gabriel.wisewallet.user.domain.models.Password;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import dev.gabriel.wisewallet.user.domain.services.EncryptPassword;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeUserPasswordCommandHandler implements CommandHandler<ChangeUserPasswordCommand> {
    private final UserRepository userEventStore;
    private final EncryptPassword encryptPassword;

    @Override
    public User handle(@NonNull ChangeUserPasswordCommand command) {
        User user = userEventStore.load(command.getAggregateId(), null).orElseThrow(() ->
                new UserNotFoundException("User %s was not found.".formatted(command.getAggregateId())));

        if(encryptPassword.validate(command.getPassword(), user.getPassword().getValue())) return user;

        Password.validate(command.getPassword());
        user.changePassword(encryptPassword.encrypt(command.getPassword()));

        userEventStore.saveChanges(user);

        return user;
    }

    @Override
    @NonNull
    public Class<ChangeUserPasswordCommand> getCommandType() {
        return ChangeUserPasswordCommand.class;
    }
}

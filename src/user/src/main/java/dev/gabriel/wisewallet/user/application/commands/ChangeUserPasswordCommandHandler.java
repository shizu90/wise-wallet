package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.user.domain.commands.ChangeUserPasswordCommand;
import dev.gabriel.wisewallet.user.domain.exceptions.UserNotFoundException;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeUserPasswordCommandHandler implements CommandHandler<ChangeUserPasswordCommand> {
    private final UserRepository userEventStore;

    @Override
    public User handle(@NonNull ChangeUserPasswordCommand command) {
        User user = userEventStore.load(command.getAggregateId()).orElseThrow(() ->
                new UserNotFoundException("User %s was not found.".formatted(command.getAggregateId())));

        if(command.getPassword().equals(user.getPassword().getValue())) return user;

        user.changePassword(command.getPassword());

        userEventStore.saveChanges(user);

        return user;
    }

    @Override
    @NonNull
    public Class<ChangeUserPasswordCommand> getCommandType() {
        return ChangeUserPasswordCommand.class;
    }
}

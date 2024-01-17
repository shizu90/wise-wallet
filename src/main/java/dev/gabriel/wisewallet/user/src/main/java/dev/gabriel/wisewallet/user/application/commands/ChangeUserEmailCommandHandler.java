package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.user.domain.commands.ChangeUserEmailCommand;
import dev.gabriel.wisewallet.user.domain.exceptions.UserNotFoundException;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeUserEmailCommandHandler implements CommandHandler<ChangeUserEmailCommand> {
    private final UserRepository userEventStore;

    @Override
    public User handle(ChangeUserEmailCommand command) {
        User user = userEventStore.load(command.getAggregateId()).orElseThrow(() ->
                new UserNotFoundException("User %s was not found.".formatted(command.getAggregateId())));

        if(command.getEmail().equals(user.getEmail().getValue())) return user;

        user.changeEmail(command.getEmail());

        userEventStore.saveChanges(user);

        return user;
    }

    @Override
    @NonNull
    public Class<ChangeUserEmailCommand> getCommandType() {
        return ChangeUserEmailCommand.class;
    }
}

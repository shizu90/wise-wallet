package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.RenameUserCommand;
import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.user.domain.exceptions.UserNotFoundException;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RenameUserCommandHandler implements CommandHandler<RenameUserCommand> {
    private final UserRepository userEventStore;

    @Override
    public User handle(@NonNull RenameUserCommand command) {
        User user = userEventStore.load(command.getAggregateId(), null).orElseThrow(() ->
                new UserNotFoundException("User %s was not found.".formatted(command.getAggregateId())));

        if(command.getName().equals(user.getName().getValue())) return user;

        user.rename(command.getName());

        userEventStore.saveChanges(user);

        return user;
    }

    @Override
    @NonNull
    public Class<RenameUserCommand> getCommandType() {
        return RenameUserCommand.class;
    }
}

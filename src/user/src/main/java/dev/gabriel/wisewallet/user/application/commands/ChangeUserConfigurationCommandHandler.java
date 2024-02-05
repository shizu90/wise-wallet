package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.ChangeUserConfigurationCommand;
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
public class ChangeUserConfigurationCommandHandler implements CommandHandler<ChangeUserConfigurationCommand> {
    private final UserRepository userEventStore;

    @Override
    public User handle(@NonNull ChangeUserConfigurationCommand command) {
        User user = userEventStore.load(command.getAggregateId(), null).orElseThrow(() ->
                new UserNotFoundException("User %s was not found".formatted(command.getAggregateId())));

        if(command.getDefaultLanguage() != null &&
           command.getDefaultLanguage().equals(user.getConfiguration().getDefaultLanguage()) &&
           command.getDefaultCurrencyCode() != null &&
           command.getDefaultCurrencyCode().equals(user.getConfiguration().getDefaultCurrencyCode())) return user;

        user.changeConfiguration(command.getDefaultCurrencyCode(), command.getDefaultLanguage());

        userEventStore.saveChanges(user);

        return user;
    }

    @Override
    @NonNull
    public Class<ChangeUserConfigurationCommand> getCommandType() {
        return ChangeUserConfigurationCommand.class;
    }
}

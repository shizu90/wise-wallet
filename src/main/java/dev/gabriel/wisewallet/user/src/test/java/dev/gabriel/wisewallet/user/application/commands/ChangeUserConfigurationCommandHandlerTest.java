package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.ChangeUserConfigurationCommand;
import dev.gabriel.wisewallet.user.domain.events.UserConfigurationChangedEvent;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class ChangeUserConfigurationCommandHandlerTest {
    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private ChangeUserConfigurationCommandHandler changeUserConfigurationCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Change of user configuration should work properly.")
    void changeConfiguration() {
        ChangeUserConfigurationCommand command = new ChangeUserConfigurationCommand(
                UUID.randomUUID(),
                "EUR",
                null
        );

        User user = User.create(
                UUID.randomUUID(),
                "Username",
                "username@email.com",
                "username123",
                "BRL",
                "PT_BR"
        );

        Mockito.when(userRepository.load(command.getAggregateId())).thenReturn(Optional.of(user));

        User returnedUser = changeUserConfigurationCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedUser.getVersion());
        Assertions.assertInstanceOf(UserConfigurationChangedEvent.class, returnedUser.getChanges().get(1));
        Assertions.assertEquals("EUR", returnedUser.getConfiguration().getDefaultCurrencyCode());
        Assertions.assertEquals("PT_BR", returnedUser.getConfiguration().getDefaultLanguage());
    }
}

package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.ChangeUserEmailCommand;
import dev.gabriel.wisewallet.user.domain.events.UserEmailChangedEvent;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import dev.gabriel.wisewallet.user.infrastructure.services.CheckUniqueEmailService;
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

public class ChangeUserEmailCommandHandlerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CheckUniqueEmailService checkUniqueEmailService;

    @Autowired
    @InjectMocks
    private ChangeUserEmailCommandHandler changeUserEmailCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Change of user email should work properly.")
    void changeEmail() {
        ChangeUserEmailCommand command = new ChangeUserEmailCommand(
                UUID.randomUUID(),
                "newusername@email.com"
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
        Mockito.when(checkUniqueEmailService.exists(command.getEmail())).thenReturn(false);

        User returnedUser = changeUserEmailCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedUser.getVersion());
        Assertions.assertInstanceOf(UserEmailChangedEvent.class, returnedUser.getChanges().get(1));
        Assertions.assertEquals(command.getEmail(), returnedUser.getEmail().getValue());
    }
}

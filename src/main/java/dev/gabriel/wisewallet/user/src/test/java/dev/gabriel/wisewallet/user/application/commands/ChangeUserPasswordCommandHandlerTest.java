package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.ChangeUserPasswordCommand;
import dev.gabriel.wisewallet.user.domain.events.UserPasswordChangedEvent;
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

public class ChangeUserPasswordCommandHandlerTest {
    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private ChangeUserPasswordCommandHandler changeUserPasswordCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Change of user password should work properly.")
    void changePassword() {
        ChangeUserPasswordCommand command = new ChangeUserPasswordCommand(
                UUID.randomUUID(),
                "newusername123"
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

        User returnedUser = changeUserPasswordCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedUser.getVersion());
        Assertions.assertInstanceOf(UserPasswordChangedEvent.class, returnedUser.getChanges().get(1));
        Assertions.assertEquals(command.getPassword(), returnedUser.getPassword().getValue());
    }
}

package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.ChangeUserEmailCommand;
import dev.gabriel.wisewallet.user.domain.models.User;
import dev.gabriel.wisewallet.user.domain.repositories.UserRepository;
import dev.gabriel.wisewallet.user.domain.services.CheckUniqueEmail;
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

public class ChangeUserEmailCommandHandlerTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CheckUniqueEmail checkUniqueEmail;
    @Autowired
    @InjectMocks
    private ChangeUserEmailCommandHandler changeUserEmailCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    private User populate() {
        return User.create(
                UUID.randomUUID(),
                "Username",
                "user@gmail.com",
                "user123",
                "BRL",
                "PT_BR"
        );
    }

    @Test
    @DisplayName("Change user email command handler tests.")
    void changeEmail() {
        User user = populate();
        ChangeUserEmailCommand command = new ChangeUserEmailCommand(user.getId(), "newemail@gmail.com");

        Mockito.when(userRepository.load(command.getAggregateId())).thenReturn(Optional.of(user));
        Mockito.when(checkUniqueEmail.exists(command.getEmail())).thenReturn(false);

        User returnedUser = changeUserEmailCommandHandler.handle(command);

        Assertions.assertEquals(command.getEmail(), returnedUser.getEmail().getValue());
    }
}

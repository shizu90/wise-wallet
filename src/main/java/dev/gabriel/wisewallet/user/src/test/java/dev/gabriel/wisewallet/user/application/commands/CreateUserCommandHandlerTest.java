package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.CreateUserCommand;
import dev.gabriel.wisewallet.user.domain.events.UserCreatedEvent;
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

import java.util.UUID;

public class CreateUserCommandHandlerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CheckUniqueEmailService checkUniqueEmailService;
    @Autowired
    @InjectMocks
    private CreateUserCommandHandler createUserCommandHandler;

    @BeforeEach
    void setupTestCase() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Creation of an user should work properly.")
    void createUser() {
        CreateUserCommand command = new CreateUserCommand(
                "Username",
                "username@email.com",
                "username123",
                "BRL",
                "PT_BR"
        );

        Mockito.when(checkUniqueEmailService.exists(command.getEmail())).thenReturn(false);

        User user = createUserCommandHandler.handle(command);

        Assertions.assertEquals(1L, user.getVersion());
        Assertions.assertInstanceOf(UserCreatedEvent.class, user.getChanges().get(0));
    }
}

package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.CreateUserCommand;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

public class CreateUserCommandHandlerTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CheckUniqueEmail checkUniqueEmail;
    @Autowired
    @InjectMocks
    private CreateUserCommandHandler createUserCommandHandler;

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
    @DisplayName("Create user command handler test")
    void createUser() {
        User user = populate();
        CreateUserCommand command = new CreateUserCommand(
                user.getId(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getConfiguration().getDefaultCurrencyCode(),
                user.getConfiguration().getDefaultLanguage()
        );

        Mockito.when(checkUniqueEmail.exists(command.getEmail())).thenReturn(false);

        User returnedUser = createUserCommandHandler.handle(command);

        Assertions.assertEquals(command.getAggregateId(), returnedUser.getId());
    }
}

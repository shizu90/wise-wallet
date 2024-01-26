package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.DeleteUserCommand;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

public class DeleteUserCommandHandlerTests {
    @Mock
    private UserRepository userRepository;
    @Autowired
    @InjectMocks
    private DeleteUserCommandHandler deleteUserCommandHandler;

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
    @DisplayName("Delete user command handler test.")
    void deleteUser() {
        User user = populate();
        DeleteUserCommand command = new DeleteUserCommand(user.getId());

        Mockito.when(userRepository.load(command.getAggregateId())).thenReturn(Optional.of(user));

        User returnedUser = deleteUserCommandHandler.handle(command);

        Assertions.assertTrue(returnedUser.getIsDeleted());
    }
}

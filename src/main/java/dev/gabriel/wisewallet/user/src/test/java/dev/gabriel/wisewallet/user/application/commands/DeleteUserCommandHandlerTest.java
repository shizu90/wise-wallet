package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.DeleteUserCommand;
import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
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

public class DeleteUserCommandHandlerTest {
    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private DeleteUserCommandHandler deleteUserCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Delete user should work properly.")
    void deleteUser() {
        DeleteUserCommand command = new DeleteUserCommand(
                UUID.randomUUID()
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

        User returnedUser = deleteUserCommandHandler.handle(command);

        Assertions.assertEquals(2L, returnedUser.getVersion());
        Assertions.assertInstanceOf(UserDeletedEvent.class, returnedUser.getChanges().get(1));
        Assertions.assertEquals(true, returnedUser.getIsDeleted());
    }
}

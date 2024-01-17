package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.RenameUserCommand;
import dev.gabriel.wisewallet.user.domain.events.UserRenamedEvent;
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

public class RenameUserCommandHandlerTest {
    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private RenameUserCommandHandler renameUserCommandHandler;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Rename of user should work properly.")
    void renameUser() {
        RenameUserCommand command = new RenameUserCommand(
                UUID.randomUUID(),
                "NewUsername"
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

        User returnedUser = renameUserCommandHandler.handle(command);

        Assertions.assertEquals(2L, user.getVersion());
        Assertions.assertInstanceOf(UserRenamedEvent.class, user.getChanges().get(1));
        Assertions.assertEquals(command.getName(), returnedUser.getName().getValue());
    }
}

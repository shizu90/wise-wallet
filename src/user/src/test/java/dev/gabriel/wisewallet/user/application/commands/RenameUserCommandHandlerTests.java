package dev.gabriel.wisewallet.user.application.commands;

import dev.gabriel.wisewallet.user.domain.commands.RenameUserCommand;
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

public class RenameUserCommandHandlerTests {
    @Mock
    private UserRepository userRepository;
    @Autowired
    @InjectMocks
    private RenameUserCommandHandler renameUserCommandHandler;

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
    @DisplayName("Rename user command handler test.")
    void renameUser() {
        User user = populate();
        RenameUserCommand command = new RenameUserCommand(user.getId(), "NewName");

        Mockito.when(userRepository.load(command.getAggregateId(), null)).thenReturn(Optional.of(user));

        User returnedUser = renameUserCommandHandler.handle(command);

        Assertions.assertEquals(command.getName(), returnedUser.getName().getValue());
    }
}

package dev.gabriel.wisewallet.user.domain;

import dev.gabriel.wisewallet.user.domain.events.*;
import dev.gabriel.wisewallet.user.domain.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserTests {
    @Test
    @DisplayName(value = "Should create user successfully.")
    void createUser() {
        User user = User.create(
                UUID.randomUUID(),
                "Username",
                "username@email.com",
                "username123",
                "BRL",
                "PT_BR"
        );

        Assertions.assertEquals(1L, user.getVersion());
        Assertions.assertInstanceOf(UserCreatedEvent.class, user.getChanges().get(0));
    }

    @Test
    @DisplayName(value = "Should rename user successfully.")
    void renameUser() {
        User user = User.create(
                UUID.randomUUID(),
                "Username",
                "username@email.com",
                "username123",
                "BRL",
                "PT_BR"
        );
        user.rename("NewUsername");

        Assertions.assertEquals(2L, user.getVersion());
        Assertions.assertInstanceOf(UserRenamedEvent.class, user.getChanges().get(1));
        Assertions.assertEquals("NewUsername", user.getName().getValue());
    }

    @Test
    @DisplayName(value = "Should change user email successfully.")
    void changeUserEmail() {
        User user = User.create(
                UUID.randomUUID(),
                "Username",
                "username@email.com",
                "username123",
                "BRL",
                "PT_BR"
        );
        user.changeEmail("newusername@email.com");

        Assertions.assertEquals(2L, user.getVersion());
        Assertions.assertInstanceOf(UserEmailChangedEvent.class, user.getChanges().get(1));
        Assertions.assertEquals("newusername@email.com", user.getEmail().getValue());
    }

    @Test
    @DisplayName(value = "Should change user password successfully.")
    void changeUserPassword() {
        User user = User.create(
                UUID.randomUUID(),
                "Username",
                "username@email.com",
                "username123",
                "BRL",
                "PT_BR"
        );
        user.changePassword("newusername123");

        Assertions.assertEquals(2L, user.getVersion());
        Assertions.assertInstanceOf(UserPasswordChangedEvent.class, user.getChanges().get(1));
        Assertions.assertEquals("newusername123", user.getPassword().getValue());
    }

    @Test
    @DisplayName(value = "Should change user default currency code successfully.")
    void changeUserDefaultCurrencyCode() {
        User user = User.create(
                UUID.randomUUID(),
                "Username",
                "username@email.com",
                "username123",
                "BRL",
                "PT_BR"
        );
        user.changeDefaultCurrencyCode("EUR");

        Assertions.assertEquals(2L, user.getVersion());
        Assertions.assertInstanceOf(UserDefaultCurrencyCodeChangedEvent.class, user.getChanges().get(1));
        Assertions.assertEquals("EUR", user.getConfiguration().getDefaultCurrencyCode());
    }

    @Test
    @DisplayName(value = "Should change user default language successfully.")
    void changeUserDefaultLanguage() {
        User user = User.create(
                UUID.randomUUID(),
                "Username",
                "username@email.com",
                "username123",
                "BRL",
                "PT_BR"
        );
        user.changeDefaultLanguage("EN_US");

        Assertions.assertEquals(2L, user.getVersion());
        Assertions.assertInstanceOf(UserDefaultLanguageChangedEvent.class, user.getChanges().get(1));
        Assertions.assertEquals("EN_US", user.getConfiguration().getDefaultLanguage());
    }
}

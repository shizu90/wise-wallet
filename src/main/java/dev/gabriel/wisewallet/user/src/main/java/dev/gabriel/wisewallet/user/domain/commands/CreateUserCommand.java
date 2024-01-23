package dev.gabriel.wisewallet.user.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateUserCommand extends UserCommand {
    private final String name;
    private final String email;
    private final String password;
    private final String defaultCurrencyCode;
    private final String defaultLanguage;

    public CreateUserCommand(UUID id, String name, String email, String password, String defaultCurrencyCode, String defaultLanguage) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.defaultLanguage = defaultLanguage;
    }
}

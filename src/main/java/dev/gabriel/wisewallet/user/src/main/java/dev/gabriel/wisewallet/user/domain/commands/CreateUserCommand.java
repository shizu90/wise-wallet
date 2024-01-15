package dev.gabriel.wisewallet.user.domain.commands;

import lombok.Getter;

@Getter
public class CreateUserCommand extends UserCommand {
    private final String name;
    private final String email;
    private final String password;
    private final String defaultCurrencyCode;
    private final String defaultLanguage;

    public CreateUserCommand(String name, String email, String password, String defaultCurrencyCode, String defaultLanguage) {
        super(null);
        this.name = name;
        this.email = email;
        this.password = password;
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.defaultLanguage = defaultLanguage;
    }
}

package dev.gabriel.wisewallet.user.domain.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeUserConfigurationCommand extends UserCommand {
    private final String defaultCurrencyCode;
    private final String defaultLanguage;

    public ChangeUserConfigurationCommand(UUID aggregateId, String defaultCurrencyCode, String defaultLanguage) {
        super(aggregateId);
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.defaultLanguage = defaultLanguage;
    }
}

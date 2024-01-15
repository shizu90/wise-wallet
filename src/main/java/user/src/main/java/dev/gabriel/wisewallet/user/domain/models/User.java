package dev.gabriel.wisewallet.user.domain.models;

import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.events.*;
import dev.gabriel.wisewallet.user.domain.exceptions.UserAlreadyDeletedException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class User extends Aggregate {
    private Username name;
    private Email email;
    private Password password;
    private UserConfiguration configuration;
    private Boolean isDeleted;

    private User(UUID id,
                 String name,
                 String email,
                 String password,
                 String defaultCurrencyCode,
                 String defaultLanguage
    ) {
        super(UserId.create(id), 0L);
        this.name = Username.create(name);
        this.email = Email.create(email);
        this.password = Password.create(password);
        this.configuration = UserConfiguration.create(defaultCurrencyCode, defaultLanguage);
    }

    public static User create(UUID id,
                              String name,
                              String email,
                              String password,
                              String defaultCurrencyCode,
                              String defaultLanguage
    ) {
        Username.validate(name);
        Email.validate(email);
        Password.validate(password);

        User user = new User(id, name, email, password, defaultCurrencyCode, defaultLanguage);

        user.applyChange(new UserCreatedEvent(
                id,
                user.getNextVersion(),
                name,
                email,
                password,
                defaultCurrencyCode,
                defaultLanguage
        ));

        return user;
    }

    public void rename(String name) {
        Username.validate(name);

        applyChange(new UserRenamedEvent(
           id.getValue(),
           getNextVersion(),
           name
        ));
    }

    public void changeEmail(String email) {
        Email.validate(email);

        applyChange(new UserEmailChangedEvent(
                id.getValue(),
                getNextVersion(),
                email
        ));
    }

    public void changePassword(String password) {
        Password.validate(password);

        applyChange(new UserPasswordChangedEvent(
                id.getValue(),
                getNextVersion(),
                password
        ));
    }

    public void changeConfiguration(String defaultCurrencyCode, String defaultLanguage) {
        boolean defaultCurrencyCodeIsEmpty = defaultCurrencyCode == null ||
                defaultCurrencyCode.isEmpty() || defaultCurrencyCode.isBlank();
        boolean defaultLanguageIsEmpty = defaultLanguage == null ||
                defaultLanguage.isEmpty() || defaultLanguage.isBlank();

        applyChange(new UserConfigurationChangedEvent(
                id.getValue(),
                getNextVersion(),
                defaultCurrencyCodeIsEmpty ? configuration.getDefaultCurrencyCode() : defaultCurrencyCode,
                defaultLanguageIsEmpty ? configuration.getDefaultLanguage() : defaultLanguage
        ));
    }

    public void delete() {
        if(isDeleted)
            throw new UserAlreadyDeletedException("User %s is already deleted.".formatted(id.getValue().toString()));

        applyChange(new UserDeletedEvent(
                id.getValue(),
                getNextVersion()
        ));
    }

    @SuppressWarnings("unused")
    public void apply(UserCreatedEvent event) {
        this.id = UserId.create(event.getAggregateId());
        this.name = Username.create(event.getName());
        this.email = Email.create(event.getEmail());
        this.password = Password.create(event.getPassword());
        this.configuration = UserConfiguration.create(event.getDefaultCurrencyCode(), event.getDefaultLanguage());
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    public void apply(UserRenamedEvent event) {
        this.name = Username.create(event.getName());
    }

    @SuppressWarnings("unused")
    public void apply(UserEmailChangedEvent event) {
        this.email = Email.create(event.getEmail());
    }

    @SuppressWarnings("unused")
    public void apply(UserPasswordChangedEvent event) {
        this.password = Password.create(event.getPassword());
    }

    @SuppressWarnings("unused")
    public void apply(UserConfigurationChangedEvent event) {
        this.configuration = UserConfiguration.create(event.getDefaultCurrencyCode(), event.getDefaultLanguage());
    }

    @SuppressWarnings("unused")
    public void apply(UserDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public String getAggregateType() {
        return AggregateType.USER.toString();
    }

    @Override
    public UserId getId() {
        return (UserId) this.id;
    }
}

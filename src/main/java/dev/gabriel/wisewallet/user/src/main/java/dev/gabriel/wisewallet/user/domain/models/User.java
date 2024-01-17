package dev.gabriel.wisewallet.user.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.events.*;
import dev.gabriel.wisewallet.user.domain.exceptions.UserAlreadyDeletedException;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class User extends Aggregate {
    private Username name;
    private Email email;
    private Password password;
    private UserConfiguration configuration;
    private Boolean isDeleted;

    @JsonCreator
    private User(@NonNull UUID id, Long version) {
        super(UserId.create(id), version);
    }

    private User(UUID id,
                 String name,
                 String email,
                 String password,
                 String defaultCurrencyCode,
                 String defaultLanguage
    ) {
        this(id, 0L);
        applyChange(new UserCreatedEvent(
                id,
                this.getNextVersion(),
                name,
                email,
                password,
                defaultCurrencyCode,
                defaultLanguage
        ));
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

        return new User(id, name, email, password, defaultCurrencyCode, defaultLanguage);
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
    private void apply(UserCreatedEvent event) {
        this.id = UserId.create(event.getAggregateId());
        this.name = Username.create(event.getName());
        this.email = Email.create(event.getEmail());
        this.password = Password.create(event.getPassword());
        this.configuration = UserConfiguration.create(event.getDefaultCurrencyCode(), event.getDefaultLanguage());
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(UserRenamedEvent event) {
        this.name = Username.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(UserEmailChangedEvent event) {
        this.email = Email.create(event.getEmail());
    }

    @SuppressWarnings("unused")
    private void apply(UserPasswordChangedEvent event) {
        this.password = Password.create(event.getPassword());
    }

    @SuppressWarnings("unused")
    private void apply(UserConfigurationChangedEvent event) {
        this.configuration = UserConfiguration.create(event.getDefaultCurrencyCode(), event.getDefaultLanguage());
    }

    @SuppressWarnings("unused")
    private void apply(UserDeletedEvent event) {
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

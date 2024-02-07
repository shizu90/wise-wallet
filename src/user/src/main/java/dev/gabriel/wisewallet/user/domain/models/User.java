package dev.gabriel.wisewallet.user.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.user.domain.exceptions.UserAlreadyDeletedException;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.events.*;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Getter
public class User extends Aggregate {
    private Username name;
    private Email email;
    private Password password;
    private UserConfiguration configuration;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    @JsonCreator
    private User(@NonNull UUID id, Long version) {
        super(id, version);
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
                getNextVersion(),
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
        UserConfiguration.validateCurrencyCode(defaultCurrencyCode);
        UserConfiguration.validateLanguage(defaultLanguage);

        return new User(id, name, email, password, defaultCurrencyCode, defaultLanguage);
    }

    public void rename(String name) {
        Username.validate(name);

        applyChange(new UserRenamedEvent(
           id,
           getNextVersion(),
           name
        ));
    }

    public void changeEmail(String email) {
        Email.validate(email);

        applyChange(new UserEmailChangedEvent(
                id,
                getNextVersion(),
                email
        ));
    }

    public void changePassword(String password) {
        Password.validate(password);

        applyChange(new UserPasswordChangedEvent(
                id,
                getNextVersion(),
                password
        ));
    }

    public void changeDefaultCurrencyCode(String defaultCurrencyCode) {
        UserConfiguration.validateCurrencyCode(defaultCurrencyCode);

        applyChange(new UserDefaultCurrencyCodeChangedEvent(id, getNextVersion(), defaultCurrencyCode));
    }

    public void changeDefaultLanguage(String defaultLanguage) {
        UserConfiguration.validateLanguage(defaultLanguage);

        applyChange(new UserDefaultLanguageChangedEvent(id, getNextVersion(), defaultLanguage));
    }

    public void delete() {
        if(isDeleted)
            throw new UserAlreadyDeletedException("User %s is already deleted.".formatted(id.toString()));

        applyChange(new UserDeletedEvent(
                id,
                getNextVersion()
        ));
    }

    @SuppressWarnings("unused")
    private void apply(UserCreatedEvent event) {
        this.id = event.getAggregateId();
        this.name = Username.create(event.getName());
        this.email = Email.create(event.getEmail());
        this.password = Password.create(event.getPassword());
        this.configuration = UserConfiguration.create(event.getDefaultCurrencyCode(), event.getDefaultLanguage());
        this.createdAt = Instant.now();
        this.updatedAt = null;
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(UserRenamedEvent event) {
        this.name = Username.create(event.getName());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(UserEmailChangedEvent event) {
        this.email = Email.create(event.getEmail());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(UserPasswordChangedEvent event) {
        this.password = Password.create(event.getPassword());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(UserDefaultCurrencyCodeChangedEvent event) {
        this.configuration = UserConfiguration.create(event.getCurrencyCode(), configuration.getDefaultLanguage());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(UserDefaultLanguageChangedEvent event) {
        this.configuration = UserConfiguration.create(configuration.getDefaultCurrencyCode(), event.getDefaultLanguage());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(UserDeletedEvent event) {
        this.isDeleted = true;
        this.updatedAt = Instant.now();
    }

    @Override
    public String getAggregateType() {
        return AggregateType.USER.toString();
    }
}

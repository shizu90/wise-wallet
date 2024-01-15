package dev.gabriel.wisewallet.user.domain.models;

import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.user.domain.commands.*;
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

    private User(UUID id) {
        super(UserId.create(id), 0L);
    }

    public void process(CreateUserCommand command) {
        Username.validate(command.getName());
        Email.validate(command.getEmail());
        Password.validate(command.getPassword());

        applyChange(new UserCreatedEvent(
                id.getValue(),
                getNextVersion(),
                command.getName(),
                command.getEmail(),
                command.getPassword(),
                command.getDefaultCurrencyCode(),
                command.getDefaultLanguage()
        ));
    }

    public void process(RenameUserCommand command) {
        Username.validate(command.getName());

        applyChange(new UserRenamedEvent(
           command.getAggregateId(),
           getNextVersion(),
           command.getName()
        ));
    }

    public void process(ChangeUserEmailCommand command) {
        Email.validate(command.getEmail());

        applyChange(new UserEmailChangedEvent(
                command.getAggregateId(),
                getNextVersion(),
                command.getEmail()
        ));
    }

    public void process(ChangeUserPasswordCommand command) {
        Password.validate(command.getPassword());

        applyChange(new UserPasswordChangedEvent(
                command.getAggregateId(),
                getNextVersion(),
                command.getPassword()
        ));
    }

    public void process(ChangeUserConfigurationCommand command) {
        boolean defaultCurrencyCodeIsEmpty = command.getDefaultCurrencyCode() == null ||
                command.getDefaultCurrencyCode().isEmpty() || command.getDefaultCurrencyCode().isBlank();
        boolean defaultLanguageIsEmpty = command.getDefaultLanguage() == null ||
                command.getDefaultLanguage().isEmpty() || command.getDefaultLanguage().isBlank();

        applyChange(new UserConfigurationChangedEvent(
                command.getAggregateId(),
                getNextVersion(),
                defaultCurrencyCodeIsEmpty ? configuration.getDefaultCurrencyCode() : command.getDefaultCurrencyCode(),
                defaultLanguageIsEmpty ? configuration.getDefaultLanguage() : command.getDefaultLanguage()
        ));
    }

    public void process(DeleteUserCommand command) {
        if(isDeleted)
            throw new UserAlreadyDeletedException("User %s is already deleted.".formatted(id.getValue().toString()));

        applyChange(new UserDeletedEvent(
                command.getAggregateId(),
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

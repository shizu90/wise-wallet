package dev.gabriel.wisewallet.user.domain.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EventType {
    USER_CREATED(UserCreatedEvent.class),
    USER_RENAMED(UserRenamedEvent.class),
    USER_EMAIL_CHANGED(UserEmailChangedEvent.class),
    USER_PASSWORD_CHANGED(UserPasswordChangedEvent.class),
    USER_DEFAULT_CURRENCY_CODE_CHANGED(UserDefaultCurrencyCodeChangedEvent.class),
    USER_DEFAULT_LANGUAGE_CHANGED(UserDefaultLanguageChangedEvent.class),
    USER_DELETED(UserDeletedEvent.class);

    private final Class<? extends UserEvent> type;
}

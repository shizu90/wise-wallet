package dev.gabriel.wisewallet.user.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserDefaultLanguageChangedEvent extends UserEvent {
    private final String defaultLanguage;

    @JsonCreator
    public UserDefaultLanguageChangedEvent(UUID id, Long version, String defaultLanguage) {
        super(id, version);
        this.defaultLanguage = defaultLanguage;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.USER_DEFAULT_LANGUAGE_CHANGED.toString();
    }
}

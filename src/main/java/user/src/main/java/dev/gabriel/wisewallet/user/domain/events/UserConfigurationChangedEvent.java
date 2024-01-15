package dev.gabriel.wisewallet.user.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserConfigurationChangedEvent extends UserEvent {
    private final String defaultCurrencyCode;
    private final String defaultLanguage;

    public UserConfigurationChangedEvent(UUID aggregateId,
                                         Long version,
                                         String defaultCurrencyCode,
                                         String defaultLanguage
    ) {
        super(aggregateId, version);
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.defaultLanguage = defaultLanguage;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.USER_CONFIGURATION_CHANGED.toString();
    }
}

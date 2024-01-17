package dev.gabriel.wisewallet.user.domain.events;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserCreatedEvent extends UserEvent {
    private final String name;
    private final String email;
    private final String password;
    private final String defaultCurrencyCode;
    private final String defaultLanguage;

    @JsonCreator
    public UserCreatedEvent(UUID aggregateId,
                            Long version,
                            String name,
                            String email,
                            String password,
                            String defaultCurrencyCode,
                            String defaultLanguage
    ) {
        super(aggregateId, version);
        this.name = name;
        this.email = email;
        this.password = password;
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.defaultLanguage = defaultLanguage;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.USER_CREATED.toString();
    }
}

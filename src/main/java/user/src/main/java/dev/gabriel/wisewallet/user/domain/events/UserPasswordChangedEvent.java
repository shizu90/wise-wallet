package dev.gabriel.wisewallet.user.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserPasswordChangedEvent extends UserEvent {
    private final String password;

    public UserPasswordChangedEvent(UUID aggregateId, Long version, String password) {
        super(aggregateId, version);
        this.password = password;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.USER_PASSWORD_CHANGED.toString();
    }
}

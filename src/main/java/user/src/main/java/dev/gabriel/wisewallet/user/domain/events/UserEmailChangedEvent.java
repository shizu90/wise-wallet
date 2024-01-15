package dev.gabriel.wisewallet.user.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserEmailChangedEvent extends UserEvent {
    private final String email;

    public UserEmailChangedEvent(UUID aggregateId, Long version, String email) {
        super(aggregateId, version);
        this.email = email;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.USER_EMAIL_CHANGED.toString();
    }
}

package dev.gabriel.wisewallet.user.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserDeletedEvent extends UserEvent {
    public UserDeletedEvent(UUID aggregateId, Long version) {
        super(aggregateId, version);
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.USER_DELETED.toString();
    }
}

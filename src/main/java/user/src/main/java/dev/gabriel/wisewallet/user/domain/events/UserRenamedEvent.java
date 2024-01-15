package dev.gabriel.wisewallet.user.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserRenamedEvent extends UserEvent {
    private final String name;

    public UserRenamedEvent(UUID aggregateId, Long version, String name) {
        super(aggregateId, version);
        this.name = name;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.USER_RENAMED.toString();
    }
}

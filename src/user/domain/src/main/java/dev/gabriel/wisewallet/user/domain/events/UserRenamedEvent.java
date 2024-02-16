package dev.gabriel.wisewallet.user.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UserRenamedEvent extends UserEvent {
    private final String name;

    @JsonCreator
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

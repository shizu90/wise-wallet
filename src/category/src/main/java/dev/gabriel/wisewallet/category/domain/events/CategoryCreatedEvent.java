package dev.gabriel.wisewallet.category.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class CategoryCreatedEvent extends CategoryEvent {
    private final String name;
    private final UUID userId;

    public CategoryCreatedEvent(UUID id, Long version, String name, UUID userId) {
        super(id, version);
        this.name = name;
        this.userId = userId;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.CATEGORY_CREATED.toString();
    }
}

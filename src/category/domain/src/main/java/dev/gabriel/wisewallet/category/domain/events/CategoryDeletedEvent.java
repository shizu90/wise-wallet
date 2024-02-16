package dev.gabriel.wisewallet.category.domain.events;

import lombok.NonNull;

import java.util.UUID;

public class CategoryDeletedEvent extends CategoryEvent {
    public CategoryDeletedEvent(UUID id, Long version) {
        super(id, version);
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.CATEGORY_DELETED.toString();
    }
}

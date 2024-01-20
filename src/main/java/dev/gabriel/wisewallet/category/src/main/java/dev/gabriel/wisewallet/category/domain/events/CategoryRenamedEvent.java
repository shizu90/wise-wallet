package dev.gabriel.wisewallet.category.domain.events;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class CategoryRenamedEvent extends CategoryEvent {
    private final String name;

    public CategoryRenamedEvent(UUID id, Long version, String name) {
        super(id, version);
        this.name = name;
    }

    @Override
    @NonNull
    public String getEventType() {
        return EventType.CATEGORY_RENAMED.toString();
    }
}

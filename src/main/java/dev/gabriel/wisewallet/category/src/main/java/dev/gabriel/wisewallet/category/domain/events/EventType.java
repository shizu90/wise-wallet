package dev.gabriel.wisewallet.category.domain.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EventType {
    CATEGORY_CREATED(CategoryCreatedEvent.class),
    CATEGORY_RENAMED(CategoryRenamedEvent.class),
    CATEGORY_DELETED(CategoryDeletedEvent.class);

    private final Class<? extends CategoryEvent> type;
}

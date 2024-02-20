package dev.gabriel.wisewallet.category.application.events;

import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;

public interface CategoryAsyncEventHandler {
    void handle(UserDeletedEvent event);
}

package dev.gabriel.wisewallet.core.application;

import dev.gabriel.wisewallet.core.domain.events.DomainEvent;
import dev.gabriel.wisewallet.core.domain.events.DomainEventWithId;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import lombok.NonNull;

import java.util.List;

public interface EventHandler {
    void handleEvents(List<DomainEventWithId<DomainEvent>> events, Aggregate aggregate);

    @NonNull
    String getAggregateType();
}

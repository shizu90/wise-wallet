package dev.gabriel.wisewallet.core.application;

import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import lombok.NonNull;

public interface SyncEventHandler {
    void handleEvents(Aggregate aggregate);

    @NonNull
    String getAggregateType();
}

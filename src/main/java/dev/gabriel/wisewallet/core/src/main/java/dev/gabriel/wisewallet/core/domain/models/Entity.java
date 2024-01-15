package dev.gabriel.wisewallet.core.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Entity {
    protected Identity id;

    public abstract Identity getId();
}

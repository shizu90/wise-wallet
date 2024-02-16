package dev.gabriel.wisewallet.core.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Entity {
    protected UUID id;
}

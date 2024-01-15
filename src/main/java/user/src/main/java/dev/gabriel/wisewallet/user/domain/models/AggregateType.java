package dev.gabriel.wisewallet.user.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AggregateType {
    USER(User.class);

    private final Class<User> type;
}

package dev.gabriel.wisewallet.category.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AggregateType {
    CATEGORY(Category.class);

    private final Class<Category> type;
}

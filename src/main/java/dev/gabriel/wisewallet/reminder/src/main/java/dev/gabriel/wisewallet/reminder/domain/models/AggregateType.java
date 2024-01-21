package dev.gabriel.wisewallet.reminder.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AggregateType {
    REMINDER(Reminder.class);

    private final Class<Reminder> type;
}

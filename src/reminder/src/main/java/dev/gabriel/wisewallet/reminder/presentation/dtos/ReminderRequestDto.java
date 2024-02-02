package dev.gabriel.wisewallet.reminder.presentation.dtos;

import java.util.UUID;

public record ReminderRequestDto(
        UUID id,
        String name,
        String description,
        Long recurrence,
        Long maxRuns,
        UUID userId
) {}

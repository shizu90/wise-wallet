package dev.gabriel.wisewallet.reminder.presentation.dtos;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ReminderResponseDto(
        UUID id,
        String name,
        String description,
        Long recurrence,
        Long maxRuns,
        Long currentRuns,
        LocalDate lastRun,
        Boolean started,
        UUID userId,
        Instant createdAt,
        Instant updatedAt
) {}

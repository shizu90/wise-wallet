package dev.gabriel.wisewallet.reminder.infrastructure.services.dtos;

import java.util.List;

public record ReminderListResponseDto(
        List<ReminderResponseDto> reminders,
        long totalElements,
        long totalPages
) {}

package dev.gabriel.wisewallet.reminder.presentation.dtos;

import java.util.List;

public record ReminderListResponseDto(
        List<ReminderResponseDto> reminders,
        long totalElements,
        long totalPages
) {}

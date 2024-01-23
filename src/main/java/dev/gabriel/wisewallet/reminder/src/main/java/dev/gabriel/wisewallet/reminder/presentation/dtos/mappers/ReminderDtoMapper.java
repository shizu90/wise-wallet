package dev.gabriel.wisewallet.reminder.presentation.dtos.mappers;

import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjection;
import dev.gabriel.wisewallet.reminder.presentation.dtos.ReminderListResponseDto;
import dev.gabriel.wisewallet.reminder.presentation.dtos.ReminderResponseDto;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ReminderDtoMapper {
    public ReminderResponseDto toResponseDto(@Nullable Reminder reminder) {
        if(reminder == null) return null;
        return new ReminderResponseDto(
                reminder.getId(),
                reminder.getName().getValue(),
                reminder.getDescription().getValue(),
                reminder.getRecurrence().getValue(),
                reminder.getMaxRuns().getValue(),
                reminder.getCurrentRuns().getValue(),
                reminder.getLastRun(),
                reminder.getStarted(),
                reminder.getUserId()
        );
    }

    public ReminderResponseDto toResponseDto(@Nullable ReminderProjection reminderProjection) {
        if(reminderProjection == null) return null;
        return new ReminderResponseDto(
                reminderProjection.getId(),
                reminderProjection.getName(),
                reminderProjection.getDescription(),
                reminderProjection.getRecurrence(),
                reminderProjection.getMaxRuns(),
                reminderProjection.getCurrentRuns(),
                reminderProjection.getLastRun(),
                reminderProjection.getStarted(),
                reminderProjection.getUserId()
        );
    }

    public ReminderListResponseDto toResponseDto(@Nullable Page<ReminderProjection> reminderProjectionList) {
        if(reminderProjectionList == null) return null;
        return new ReminderListResponseDto(
                reminderProjectionList.getContent().stream().map(this::toResponseDto).toList(),
                reminderProjectionList.getTotalElements(),
                reminderProjectionList.getTotalPages()
        );
    }
}

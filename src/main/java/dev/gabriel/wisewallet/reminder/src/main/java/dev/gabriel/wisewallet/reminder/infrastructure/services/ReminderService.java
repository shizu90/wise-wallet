package dev.gabriel.wisewallet.reminder.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.reminder.domain.commands.*;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjectionRepository;
import dev.gabriel.wisewallet.reminder.presentation.dtos.ReminderListResponseDto;
import dev.gabriel.wisewallet.reminder.presentation.dtos.ReminderRequestDto;
import dev.gabriel.wisewallet.reminder.presentation.dtos.ReminderResponseDto;
import dev.gabriel.wisewallet.reminder.presentation.dtos.mappers.ReminderDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReminderService {
    private final CommandBus<ReminderCommand> commandBus;
    private final ReminderProjectionRepository reminderProjectionRepository;
    private final ReminderDtoMapper reminderDtoMapper;

    public ReminderResponseDto getById(UUID id) {
        return reminderDtoMapper.toResponseDto(reminderProjectionRepository.find(id).orElse(null));
    }

    public ReminderListResponseDto get(UUID userId, String name, int page, int limit) {
        if(name == null)
            return reminderDtoMapper.toResponseDto(reminderProjectionRepository.findByUserId(userId, PageRequest.of(page, limit)));

        return reminderDtoMapper.toResponseDto(reminderProjectionRepository.findByUserIdAndName(userId, name, PageRequest.of(page, limit)));
    }

    public ReminderResponseDto create(ReminderRequestDto request) {
        Reminder reminder = (Reminder) commandBus.execute(new CreateReminderCommand(
                request.id(),
                request.name(),
                request.description(),
                request.recurrence(),
                request.maxRuns(),
                request.userId()
        ));

        return reminderDtoMapper.toResponseDto(reminder);
    }

    public ReminderResponseDto update(ReminderRequestDto request) {
        Reminder reminder = null;

        if(!(request.name() == null || request.name().isEmpty() || request.name().isBlank())) {
            reminder = (Reminder) commandBus.execute(new RenameReminderCommand(request.id(), request.name()));
        }
        if(!(request.description() == null || request.description().isEmpty() || request.description().isBlank())) {
            reminder = (Reminder) commandBus.execute(new ChangeReminderDescriptionCommand(request.id(), request.description()));
        }
        if(!(request.recurrence() == null)) {
            reminder = (Reminder) commandBus.execute(new ChangeReminderRecurrenceCommand(request.id(), request.recurrence()));
        }
        if(!(request.maxRuns() == null)) {
            reminder = (Reminder) commandBus.execute(new ChangeReminderMaxRunsCommand(request.id(), request.maxRuns()));
        }

        return reminderDtoMapper.toResponseDto(reminder);
    }

    public void start(UUID id) {
        commandBus.execute(new StartReminderCommand(id));
    }

    public void stop(UUID id) {
        commandBus.execute(new StopReminderCommand(id));
    }

    public void reset(UUID id) {
        commandBus.execute(new ResetReminderCommand(id));
    }

    public void delete(UUID id) {
        commandBus.execute(new DeleteReminderCommand(id));
    }
}

package dev.gabriel.wisewallet.reminder.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.reminder.domain.commands.ExecuteReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.commands.ReminderCommand;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjection;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReminderNotificationService {
    private final ReminderProjectionRepository reminderProjectionRepository;
    private final CommandBus<ReminderCommand> commandBus;

    @Async
    @Scheduled(cron = "0 0 8 * * *")
    public void execute() {
        LocalDate today = LocalDate.now();

        List<ReminderProjection> reminders = reminderProjectionRepository
                .findStartedReminders()
                .stream()
                .filter(reminder -> reminder.getLastRun().plusDays(reminder.getRecurrence()).equals(today))
                .toList();

        for(ReminderProjection reminder : reminders) {
            commandBus.execute(new ExecuteReminderCommand(reminder.getId(), 1L));
        }
    }
}

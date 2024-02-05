package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.reminder.domain.commands.ResetReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderNotFoundException;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResetReminderCommandHandler implements CommandHandler<ResetReminderCommand> {
    private final ReminderRepository reminderRepository;

    @Override
    public Reminder handle(@NonNull ResetReminderCommand command) {
        Reminder reminder = reminderRepository.load(command.getAggregateId(), null).orElseThrow(() ->
                new ReminderNotFoundException("Reminder %s was not found.".formatted(command.getAggregateId())));

        if(reminder.getCurrentRuns().getValue().equals(0L)) return reminder;

        reminder.reset();

        reminderRepository.saveChanges(reminder);

        return reminder;
    }

    @Override
    @NonNull
    public Class<ResetReminderCommand> getCommandType() {
        return ResetReminderCommand.class;
    }
}

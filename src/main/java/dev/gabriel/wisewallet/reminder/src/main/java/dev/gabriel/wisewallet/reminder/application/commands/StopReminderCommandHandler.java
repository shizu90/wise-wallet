package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.reminder.domain.commands.StopReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderNotFoundException;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StopReminderCommandHandler implements CommandHandler<StopReminderCommand> {
    private final ReminderRepository reminderRepository;

    @Override
    public Reminder handle(@NonNull StopReminderCommand command) {
        Reminder reminder = reminderRepository.load(command.getAggregateId()).orElseThrow(() ->
                new ReminderNotFoundException("Reminder %s was not found.".formatted(command.getAggregateId())));

        if(reminder.getStarted().equals(false)) return reminder;

        reminder.stop();

        reminderRepository.saveChanges(reminder);

        return reminder;
    }

    @Override
    @NonNull
    public Class<StopReminderCommand> getCommandType() {
        return StopReminderCommand.class;
    }
}

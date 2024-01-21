package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.reminder.domain.commands.StartReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderNotFoundException;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StartReminderCommandHandler implements CommandHandler<StartReminderCommand> {
    private final ReminderRepository reminderRepository;

    @Override
    public Reminder handle(@NonNull StartReminderCommand command) {
        Reminder reminder = reminderRepository.load(command.getAggregateId()).orElseThrow(() ->
                new ReminderNotFoundException("Reminder %s was not found.".formatted(command.getAggregateId())));

        if(reminder.getStarted().equals(true)) return reminder;

        reminder.start();

        reminderRepository.saveChanges(reminder);

        return reminder;
    }

    @Override
    @NonNull
    public Class<StartReminderCommand> getCommandType() {
        return StartReminderCommand.class;
    }
}

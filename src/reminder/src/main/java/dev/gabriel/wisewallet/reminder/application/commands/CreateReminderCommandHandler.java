package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.reminder.domain.commands.CreateReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderAlreadyExistsException;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
import dev.gabriel.wisewallet.reminder.domain.services.CheckUniqueReminderName;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateReminderCommandHandler implements CommandHandler<CreateReminderCommand> {
    private final ReminderRepository reminderRepository;
    private final CheckUniqueReminderName checkUniqueReminderName;

    @Override
    public Reminder handle(@NonNull CreateReminderCommand command) {
        if(checkUniqueReminderName.exists(command.getName(), command.getUserId()))
            throw new ReminderAlreadyExistsException("Reminder with name %s already exists.".formatted(command.getName()));

        Reminder reminder = Reminder.create(
                command.getAggregateId(),
                command.getName(),
                command.getDescription(),
                command.getRecurrence(),
                command.getMaxRuns(),
                command.getUserId()
        );

        reminderRepository.saveChanges(reminder);

        return reminder;
    }

    @Override
    @NonNull
    public Class<CreateReminderCommand> getCommandType() {
        return CreateReminderCommand.class;
    }
}

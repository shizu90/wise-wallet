package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.reminder.domain.commands.RenameReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderAlreadyExistsException;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderNotFoundException;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
import dev.gabriel.wisewallet.reminder.domain.services.CheckUniqueReminderName;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RenameReminderCommandHandler implements CommandHandler<RenameReminderCommand> {
    private final ReminderRepository reminderRepository;
    private final CheckUniqueReminderName checkUniqueReminderName;

    @Override
    public Reminder handle(@NonNull RenameReminderCommand command) {
        Reminder reminder = reminderRepository.load(command.getAggregateId()).orElseThrow(() ->
                new ReminderNotFoundException("Reminder %s was not found.".formatted(command.getAggregateId())));

        if(reminder.getName().getValue().equals(command.getName())) return reminder;

        if(checkUniqueReminderName.exists(command.getName(), reminder.getUserId())) {
            throw new ReminderAlreadyExistsException("Reminder with name %s already exists.".formatted(command.getName()));
        }

        reminder.rename(command.getName());

        reminderRepository.saveChanges(reminder);

        return reminder;
    }

    @Override
    @NonNull
    public Class<RenameReminderCommand> getCommandType() {
        return RenameReminderCommand.class;
    }
}

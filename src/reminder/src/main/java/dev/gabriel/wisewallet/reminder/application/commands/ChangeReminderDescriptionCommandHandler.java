package dev.gabriel.wisewallet.reminder.application.commands;

import dev.gabriel.wisewallet.core.application.CommandHandler;
import dev.gabriel.wisewallet.reminder.domain.commands.ChangeReminderDescriptionCommand;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderNotFoundException;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChangeReminderDescriptionCommandHandler implements CommandHandler<ChangeReminderDescriptionCommand> {
    private final ReminderRepository reminderRepository;

    @Override
    public Reminder handle(@NonNull ChangeReminderDescriptionCommand command) {
        Reminder reminder = reminderRepository.load(command.getAggregateId()).orElseThrow(() ->
                new ReminderNotFoundException("Reminder %s was not found.".formatted(command.getAggregateId())));

        if(reminder.getDescription().getValue().equals(command.getDescription())) return reminder;

        reminder.changeDescription(command.getDescription());

        reminderRepository.saveChanges(reminder);

        return reminder;
    }

    @Override
    @NonNull
    public Class<ChangeReminderDescriptionCommand> getCommandType() {
        return ChangeReminderDescriptionCommand.class;
    }
}

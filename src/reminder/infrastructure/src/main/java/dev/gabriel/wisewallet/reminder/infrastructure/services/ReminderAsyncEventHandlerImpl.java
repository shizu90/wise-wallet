package dev.gabriel.wisewallet.reminder.infrastructure.services;

import dev.gabriel.wisewallet.core.application.CommandBus;
import dev.gabriel.wisewallet.reminder.application.events.ReminderAsyncEventHandler;
import dev.gabriel.wisewallet.reminder.domain.commands.DeleteReminderCommand;
import dev.gabriel.wisewallet.reminder.domain.commands.ReminderCommand;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjection;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjectionRepository;
import dev.gabriel.wisewallet.user.domain.events.UserDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReminderAsyncEventHandlerImpl implements ReminderAsyncEventHandler {
    private final ReminderProjectionRepository reminderProjectionRepository;
    private final CommandBus<ReminderCommand> commandBus;

    @Override
    public void handle(UserDeletedEvent event) {
        List<ReminderProjection> reminders = reminderProjectionRepository.findByUserId(event.getAggregateId());

        for(ReminderProjection reminder : reminders) {
            commandBus.execute(new DeleteReminderCommand(reminder.getId()));
        }
    }
}

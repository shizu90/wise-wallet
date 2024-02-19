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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReminderEventConsumer implements ReminderAsyncEventHandler {
    private final ReminderProjectionRepository reminderProjectionRepository;
    private final CommandBus<ReminderCommand> commandBus;

    @KafkaListener(topics = "UserDeletedEvent")
    @Override
    public void handle(UserDeletedEvent event, Acknowledgment ack) {
        List<ReminderProjection> reminders = reminderProjectionRepository.findByUserId(event.getAggregateId());

        for(ReminderProjection reminder : reminders) {
            commandBus.execute(new DeleteReminderCommand(reminder.getId()));
        }
    }
}

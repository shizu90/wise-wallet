package dev.gabriel.wisewallet.reminder.infrastructure.services;

import dev.gabriel.wisewallet.reminder.application.events.ReminderAsyncEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReminderEventConsumer implements ReminderAsyncEventHandler {

}

package dev.gabriel.wisewallet.reminder.infrastructure.services;

import dev.gabriel.wisewallet.reminder.domain.services.CheckUniqueReminderName;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckUniqueReminderNameService implements CheckUniqueReminderName {
    private final ReminderProjectionRepository reminderProjectionRepository;

    @Override
    public boolean exists(String name, UUID userId) {
        return !reminderProjectionRepository.findByUserIdAndName(userId, name).isEmpty();
    }
}

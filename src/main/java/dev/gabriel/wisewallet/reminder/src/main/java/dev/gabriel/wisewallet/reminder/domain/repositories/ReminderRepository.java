package dev.gabriel.wisewallet.reminder.domain.repositories;

import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;

import java.util.Optional;
import java.util.UUID;

public interface ReminderRepository extends DomainRepository {
    Optional<Reminder> load(UUID id);

    void saveChanges(Reminder reminder);
}

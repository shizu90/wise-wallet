package dev.gabriel.wisewallet.reminder.domain.repositories;

import dev.gabriel.wisewallet.core.domain.repositories.DomainRepository;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import jakarta.annotation.Nullable;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface ReminderRepository extends DomainRepository {
    Optional<Reminder> load(@NonNull UUID id, @Nullable Long version);

    void saveChanges(@NonNull Reminder reminder);
}

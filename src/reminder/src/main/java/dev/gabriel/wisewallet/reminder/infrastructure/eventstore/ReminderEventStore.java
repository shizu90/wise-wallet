package dev.gabriel.wisewallet.reminder.infrastructure.eventstore;

import dev.gabriel.wisewallet.core.infrastructure.eventstore.services.AggregateService;
import dev.gabriel.wisewallet.reminder.domain.models.AggregateType;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.domain.repositories.ReminderRepository;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReminderEventStore implements ReminderRepository {
    private final AggregateService aggregateService;

    @Override
    public Optional<Reminder> load(@NonNull UUID id, @Nullable Long version) {
        Reminder reminder = (Reminder) aggregateService.load(AggregateType.REMINDER.toString(), id, version);
        return Optional.ofNullable(reminder);
    }

    @Override
    public void saveChanges(@NonNull Reminder reminder) {
        aggregateService.save(reminder);
    }
}

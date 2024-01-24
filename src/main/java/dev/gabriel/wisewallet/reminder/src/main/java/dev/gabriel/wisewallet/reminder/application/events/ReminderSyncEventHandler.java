package dev.gabriel.wisewallet.reminder.application.events;

import dev.gabriel.wisewallet.core.application.SyncEventHandler;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.reminder.domain.models.AggregateType;
import dev.gabriel.wisewallet.reminder.domain.models.Reminder;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjection;
import dev.gabriel.wisewallet.reminder.infrastructure.projection.ReminderProjectionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReminderSyncEventHandler implements SyncEventHandler {
    private final ReminderProjectionRepository projectionRepository;

    @Override
    public void handleEvents(Aggregate aggregate) {
        Reminder reminder = (Reminder) aggregate;
        ReminderProjection reminderProjection = ReminderProjection.create(
                reminder.getId(),
                reminder.getName().getValue(),
                reminder.getDescription().getValue(),
                reminder.getRecurrence().getValue(),
                reminder.getMaxRuns().getValue(),
                reminder.getCurrentRuns().getValue(),
                reminder.getStarted(),
                reminder.getLastRun(),
                reminder.getUserId(),
                reminder.getCreatedAt(),
                reminder.getUpdatedAt(),
                reminder.getIsDeleted()
        );

        projectionRepository.save(reminderProjection);
    }

    @Override
    @NonNull
    public String getAggregateType() {
        return AggregateType.REMINDER.toString();
    }
}

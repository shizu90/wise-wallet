package dev.gabriel.wisewallet.reminder.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.gabriel.wisewallet.core.domain.models.Aggregate;
import dev.gabriel.wisewallet.reminder.domain.events.*;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderAlreadyDeletedException;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderNotStartedException;
import dev.gabriel.wisewallet.reminder.domain.exceptions.ReminderReachedMaxRunsException;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
public class Reminder extends Aggregate {
    private ReminderName name;
    private ReminderDescription description;
    private ReminderRecurrence recurrence;
    private ReminderRun maxRuns;
    private ReminderRun currentRuns;
    private Boolean started;
    private LocalDate lastRun;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted;

    @JsonCreator
    public Reminder(UUID id, Long version) {
        super(id, version);
    }

    private Reminder(UUID id,
                     String name,
                     String description,
                     Long recurrence,
                     Long maxRuns,
                     UUID userId
    ) {
        this(id, 0L);

        ReminderName.validate(name);
        ReminderDescription.validate(description);
        ReminderRun.validate(maxRuns);
        ReminderRecurrence.validate(recurrence);
        applyChange(new ReminderCreatedEvent(
                id,
                getNextVersion(),
                name,
                description,
                recurrence,
                maxRuns,
                userId
        ));
    }

    public static Reminder create(UUID id,
                                  String name,
                                  String description,
                                  Long recurrence,
                                  Long maxRuns,
                                  UUID userId
    ) {
        return new Reminder(id, name, description, recurrence, maxRuns, userId);
    }

    public void rename(String name) {
        ReminderName.validate(name);

        applyChange(new ReminderRenamedEvent(id, getNextVersion(), name));
    }

    public void changeDescription(String description) {
        ReminderDescription.validate(description);

        applyChange(new ReminderDescriptionChangedEvent(id, getNextVersion(), description));
    }

    public void changeRecurrence(Long recurrence) {
        ReminderRecurrence.validate(recurrence);

        applyChange(new ReminderRecurrenceChangedEvent(id, getNextVersion(), recurrence));
    }

    public void changeMaxRuns(Long maxRuns) {
        ReminderRun.validate(maxRuns);

        applyChange(new ReminderMaxRunsChangedEvent(id, getNextVersion(), maxRuns));
    }

    public void start() {
        if(!started)
            applyChange(new ReminderStartedEvent(id, getNextVersion()));
    }

    public void stop() {
        if(started)
            applyChange(new ReminderStoppedEvent(id, getNextVersion()));
    }

    public void reset() {
        if(currentRuns.getValue() != 0L)
            applyChange(new ReminderResetEvent(id, getNextVersion()));
    }

    public void execute(long n) {
        if(!started)
            throw new ReminderNotStartedException("Reminder %s not started".formatted(id));

        if(currentRuns.equals(maxRuns))
            throw new ReminderReachedMaxRunsException("Reminder %s reached max runs of %s.".formatted(id, maxRuns.getValue()));

        if(n == 0L) return;

        if((currentRuns.getValue() + n) < maxRuns.getValue()) {
            applyChange(new ReminderExecutedEvent(
                    id,
                    getNextVersion(),
                    currentRuns.getValue() + n,
                    LocalDate.now())
            );
        }else {
            applyChange(new ReminderExecutedEvent(
                    id,
                    getNextVersion(),
                    maxRuns.getValue(),
                    LocalDate.now())
            );
        }
    }

    public LocalDate getNextReminderDate() {
        return lastRun != null ?
                lastRun.plusDays(recurrence.getValue()) :
                LocalDate.ofInstant(createdAt.plus(recurrence.getValue(), ChronoUnit.DAYS), ZoneId.of("America/Sao_Paulo"));
    }

    public void delete() {
        if(isDeleted)
            throw new ReminderAlreadyDeletedException("Reminder %s already deleted.".formatted(id));

        applyChange(new ReminderDeletedEvent(id, getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(ReminderCreatedEvent event) {
        this.id = event.getAggregateId();
        this.name = ReminderName.create(event.getName());
        this.description = ReminderDescription.create(event.getDescription());
        this.currentRuns = ReminderRun.create(0L);
        this.maxRuns = ReminderRun.create(event.getMaxRuns());
        this.recurrence = ReminderRecurrence.create(event.getRecurrence());
        this.userId = event.getUserId();
        this.started = false;
        this.lastRun = null;
        this.createdAt = Instant.now();
        this.updatedAt = null;
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRenamedEvent event) {
        this.name = ReminderName.create(event.getName());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderDescriptionChangedEvent event) {
        this.description = ReminderDescription.create(event.getDescription());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRecurrenceChangedEvent event) {
        this.recurrence = ReminderRecurrence.create(event.getRecurrence());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderMaxRunsChangedEvent event) {
        this.maxRuns = ReminderRun.create(event.getMaxRuns());
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderStartedEvent event) {
        this.started = true;
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderStoppedEvent event) {
        this.started = false;
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderResetEvent event) {
        this.currentRuns = ReminderRun.create(0L);
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderExecutedEvent event) {
        this.currentRuns = ReminderRun.create(event.getCurrentRuns());
        this.lastRun = event.getLastRun();
        this.updatedAt = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderDeletedEvent event) {
        this.isDeleted = true;
        this.updatedAt = Instant.now();
    }

    @Override
    public String getAggregateType() {
        return AggregateType.REMINDER.toString();
    }
}

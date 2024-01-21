package dev.gabriel.wisewallet.reminder.infrastructure.projection;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "Reminders")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class ReminderProjection {
    @Id
    private UUID id;
    private String name;
    private String description;
    private Long recurrence;
    private Long maxRuns;
    private Long currentRuns;
    private Boolean started;
    private LocalDate lastRun;
    private UUID userId;
    private Boolean isDeleted;

    public static ReminderProjection create(UUID id,
                                            String name,
                                            String description,
                                            Long recurrence,
                                            Long maxRuns,
                                            Long currentRuns,
                                            Boolean started,
                                            LocalDate lastRun,
                                            UUID userId,
                                            Boolean isDeleted
    ) {
        return new ReminderProjection(id, name, description, recurrence, maxRuns, currentRuns, started, lastRun, userId, isDeleted);
    }
}

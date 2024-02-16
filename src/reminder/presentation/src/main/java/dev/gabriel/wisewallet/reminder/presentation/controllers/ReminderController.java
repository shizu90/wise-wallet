package dev.gabriel.wisewallet.reminder.presentation.controllers;

import dev.gabriel.wisewallet.reminder.infrastructure.services.ReminderService;
import dev.gabriel.wisewallet.reminder.infrastructure.services.dtos.ReminderListResponseDto;
import dev.gabriel.wisewallet.reminder.infrastructure.services.dtos.ReminderRequestDto;
import dev.gabriel.wisewallet.reminder.infrastructure.services.dtos.ReminderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/api/reminders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Reminder endpoints")
public class ReminderController {
    private final ReminderService reminderService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Returns a reminder by reminder UUID.")
    public ResponseEntity<ReminderResponseDto> getReminder(@PathVariable UUID id) {
        return ResponseEntity.ok().body(reminderService.getReminder(id));
    }

    @GetMapping
    @Operation(summary = "Returns a page of reminders by user UUID.")
    public ResponseEntity<ReminderListResponseDto> getReminders(@NonNull @RequestParam("userId") UUID userId,
                                                                @RequestParam(value = "name", required = false) String name,
                                                                @RequestParam(value = "page", required = false) Integer page,
                                                                @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity.ok().body(reminderService.getReminders(userId, name, page == null ? 0 : page, limit == null ? 4 : limit));
    }

    @PostMapping
    @Operation(summary = "Creates a reminder.")
    public ResponseEntity<ReminderResponseDto> newReminder(@RequestBody ReminderRequestDto request) {
        return ResponseEntity.ok().body(reminderService.newReminder(request));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Updates a reminder.")
    public ResponseEntity<ReminderResponseDto> updateReminderData(@PathVariable UUID id, @RequestBody ReminderRequestDto request) {
        request = new ReminderRequestDto(
                id,
                request.name(),
                request.description(),
                request.recurrence(),
                request.maxRuns(),
                request.userId()
        );

        return ResponseEntity.ok().body(reminderService.updateReminderData(request));
    }

    @PatchMapping(value = "/start/{id}")
    @Operation(summary = "Start a reminder.")
    public ResponseEntity<Void> startReminder(@PathVariable UUID id) {
        reminderService.startReminder(id);
        return ResponseEntity.ok().body(null);
    }

    @PatchMapping(value = "/stop/{id}")
    @Operation(summary = "Stop a reminder.")
    public ResponseEntity<Void> stopReminder(@PathVariable UUID id) {
        reminderService.stopReminder(id);
        return ResponseEntity.ok().body(null);
    }

    @PatchMapping(value = "/reset/{id}")
    @Operation(summary = "Reset a reminder.")
    public ResponseEntity<Void> resetReminder(@PathVariable UUID id) {
        reminderService.resetReminder(id);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a reminder.")
    public ResponseEntity<Void> deleteReminder(@PathVariable UUID id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.ok().body(null);
    }
}

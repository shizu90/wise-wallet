package dev.gabriel.wisewallet.reminder.presentation.controllers;

import dev.gabriel.wisewallet.reminder.infrastructure.services.ReminderService;
import dev.gabriel.wisewallet.reminder.presentation.dtos.ReminderListResponseDto;
import dev.gabriel.wisewallet.reminder.presentation.dtos.ReminderRequestDto;
import dev.gabriel.wisewallet.reminder.presentation.dtos.ReminderResponseDto;
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
public class ReminderController {
    private final ReminderService reminderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReminderResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(reminderService.getById(id));
    }

    @GetMapping
    public ResponseEntity<ReminderListResponseDto> get(@NonNull @RequestParam("userId") UUID userId,
                                                       @RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "page", required = false) Integer page,
                                                       @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity.ok().body(reminderService.get(userId, name, page == null ? 0 : page, limit == null ? 4 : limit));
    }

    @PostMapping
    public ResponseEntity<ReminderResponseDto> createReminder(@RequestBody ReminderRequestDto request) {
        return ResponseEntity.ok().body(reminderService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReminderResponseDto> updateReminder(@PathVariable UUID id, @RequestBody ReminderRequestDto request) {
        request = new ReminderRequestDto(
                id,
                request.name(),
                request.description(),
                request.recurrence(),
                request.maxRuns(),
                request.userId()
        );

        return ResponseEntity.ok().body(reminderService.update(request));
    }

    @PutMapping(value = "/start/{id}")
    public ResponseEntity<Void> startReminder(@PathVariable UUID id) {
        reminderService.start(id);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "/stop/{id}")
    public ResponseEntity<Void> stopReminder(@PathVariable UUID id) {
        reminderService.stop(id);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "/reset/{id}")
    public ResponseEntity<Void> resetReminder(@PathVariable UUID id) {
        reminderService.reset(id);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable UUID id) {
        reminderService.delete(id);
        return ResponseEntity.ok().body(null);
    }
}

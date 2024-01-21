package dev.gabriel.wisewallet.reminder.presentation.controllers;

import dev.gabriel.wisewallet.core.presentation.exceptions.ControllerException;
import dev.gabriel.wisewallet.reminder.domain.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@SuppressWarnings("unused")
@RestControllerAdvice
public class ReminderControllerExceptionHandler {
    @ExceptionHandler(ReminderValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(ReminderValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ReminderAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(ReminderAlreadyDeletedException e, HttpServletRequest request) {
        String error = "Already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ReminderAlreadyExistsException.class)
    public ResponseEntity<ControllerException> alreadyExists(ReminderAlreadyExistsException e, HttpServletRequest request) {
        String error = "Already exists.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ReminderNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(ReminderNotFoundException e, HttpServletRequest request) {
        String error = "Not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ReminderNotStartedException.class)
    public ResponseEntity<ControllerException> notStarted(ReminderNotStartedException e, HttpServletRequest request) {
        String error = "Not started.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ReminderReachedMaxRunsException.class)
    public ResponseEntity<ControllerException> reachedMaxRuns(ReminderReachedMaxRunsException e, HttpServletRequest request) {
        String error = "Reached max runs.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

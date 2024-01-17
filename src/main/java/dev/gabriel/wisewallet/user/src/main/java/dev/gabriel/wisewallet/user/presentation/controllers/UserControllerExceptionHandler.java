package dev.gabriel.wisewallet.user.presentation.controllers;

import dev.gabriel.wisewallet.core.presentation.exceptions.ControllerException;
import dev.gabriel.wisewallet.user.domain.exceptions.UserAlreadyDeletedException;
import dev.gabriel.wisewallet.user.domain.exceptions.UserAlreadyExistsException;
import dev.gabriel.wisewallet.user.domain.exceptions.UserNotFoundException;
import dev.gabriel.wisewallet.user.domain.exceptions.UserValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@SuppressWarnings("unused")
@RestControllerAdvice
public class UserControllerExceptionHandler {
    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(UserValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(UserAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(UserAlreadyDeletedException e, HttpServletRequest request) {
        String error = "User already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ControllerException> alreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {
        String error = "User already exists.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(UserNotFoundException e, HttpServletRequest request) {
        String error = "User not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

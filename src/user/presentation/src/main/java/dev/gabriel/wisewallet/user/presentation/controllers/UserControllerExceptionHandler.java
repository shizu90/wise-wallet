package dev.gabriel.wisewallet.user.presentation.controllers;

import dev.gabriel.wisewallet.user.domain.exceptions.UserAlreadyDeletedException;
import dev.gabriel.wisewallet.core.presentation.exceptions.ControllerException;
import dev.gabriel.wisewallet.user.domain.exceptions.UserAlreadyExistsException;
import dev.gabriel.wisewallet.user.domain.exceptions.UserNotFoundException;
import dev.gabriel.wisewallet.user.domain.exceptions.UserValidationException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@SuppressWarnings("unused")
@RestControllerAdvice
public class UserControllerExceptionHandler {
    @ApiResponse(responseCode = "400", description = "Validation failed.")
    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(UserValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ApiResponse(responseCode = "409", description = "Already exists/already deleted.")
    @ExceptionHandler(UserAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(UserAlreadyDeletedException e, HttpServletRequest request) {
        String error = "Already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ApiResponse(responseCode = "409", description = "Already exists/already deleted.")
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ControllerException> alreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {
        String error = "Already exists.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ApiResponse(responseCode = "404", description = "Not found.")
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(UserNotFoundException e, HttpServletRequest request) {
        String error = "Not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

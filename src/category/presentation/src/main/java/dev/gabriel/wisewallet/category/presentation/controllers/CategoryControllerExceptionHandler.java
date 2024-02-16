package dev.gabriel.wisewallet.category.presentation.controllers;

import dev.gabriel.wisewallet.category.domain.exceptions.CategoryAlreadyDeletedException;
import dev.gabriel.wisewallet.category.domain.exceptions.CategoryAlreadyExistsException;
import dev.gabriel.wisewallet.category.domain.exceptions.CategoryNotFoundException;
import dev.gabriel.wisewallet.category.domain.exceptions.CategoryValidationException;
import dev.gabriel.wisewallet.core.presentation.exceptions.ControllerException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@SuppressWarnings("unused")
@RestControllerAdvice
public class CategoryControllerExceptionHandler {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Validation failed.")
    @ExceptionHandler(CategoryValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(CategoryValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already deleted.")
    @ExceptionHandler(CategoryAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(CategoryAlreadyDeletedException e, HttpServletRequest request) {
        String error = "Already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already exists.")
    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ControllerException> alreadyExists(CategoryAlreadyExistsException e, HttpServletRequest request) {
        String error = "Already exists.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found.")
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(CategoryNotFoundException e, HttpServletRequest request) {
        String error = "Not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@SuppressWarnings("unused")
public class CategoryControllerExceptionHandler {
    @ExceptionHandler(CategoryValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(CategoryValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(CategoryAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(CategoryAlreadyDeletedException e, HttpServletRequest request) {
        String error = "Already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ControllerException> alreadyExists(CategoryAlreadyExistsException E, HttpServletRequest request) {
        String error = "Already exists.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(CategoryNotFoundException e, HttpServletRequest request) {
        String error = "Not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

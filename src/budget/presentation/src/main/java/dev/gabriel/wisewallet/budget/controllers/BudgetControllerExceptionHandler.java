package dev.gabriel.wisewallet.budget.controllers;

import dev.gabriel.wisewallet.budget.domain.exceptions.*;
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
public class BudgetControllerExceptionHandler {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Validation failed.")
    @ExceptionHandler(BudgetValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(BudgetValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already deleted.")
    @ExceptionHandler(BudgetAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(BudgetAlreadyDeletedException e, HttpServletRequest request) {
        String error = "Already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already exists.")
    @ExceptionHandler(BudgetAlreadyExistsException.class)
    public ResponseEntity<ControllerException> alreadyExists(BudgetAlreadyExistsException e, HttpServletRequest request) {
        String error = "Already exists.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found.")
    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(BudgetNotFoundException e, HttpServletRequest request) {
        String error = "Not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Reached max items.")
    @ExceptionHandler(BudgetReachedMaxItemsException.class)
    public ResponseEntity<ControllerException> reachedMaxItems(BudgetReachedMaxItemsException e, HttpServletRequest request) {
        String error = "Reached max items.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Not present.")
    @ExceptionHandler(BudgetItemNotPresentException.class)
    public ResponseEntity<ControllerException> notPresent(BudgetItemNotPresentException e, HttpServletRequest request) {
        String error = "Not present.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Already present.")
    @ExceptionHandler(BudgetItemAlreadyPresentException.class)
    public ResponseEntity<ControllerException> alreadyPresent(BudgetItemAlreadyPresentException e, HttpServletRequest request) {
        String error = "Already present.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

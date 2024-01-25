package dev.gabriel.wisewallet.bill.presentation.controllers;

import dev.gabriel.wisewallet.bill.domain.exceptions.BillAlreadyDeletedException;
import dev.gabriel.wisewallet.bill.domain.exceptions.BillNotFoundException;
import dev.gabriel.wisewallet.bill.domain.exceptions.BillValidationException;
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
public class BillControllerExceptionHandler {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Validation failed.")
    @ExceptionHandler(BillValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(BillValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already deleted.")
    @ExceptionHandler(BillAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(BillAlreadyDeletedException e, HttpServletRequest request) {
        String error = "Already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found.")
    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(BillNotFoundException e, HttpServletRequest request) {
        String error = "Not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

package dev.gabriel.wisewallet.presentation.controllers;

import dev.gabriel.wisewallet.core.presentation.exceptions.ControllerException;
import dev.gabriel.wisewallet.domain.exceptions.WalletAlreadyDeletedException;
import dev.gabriel.wisewallet.domain.exceptions.WalletAlreadyExistsException;
import dev.gabriel.wisewallet.domain.exceptions.WalletNotFoundException;
import dev.gabriel.wisewallet.domain.exceptions.WalletValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@SuppressWarnings("unused")
@RestControllerAdvice
public class WalletControllerExceptionHandler {
    @ExceptionHandler(WalletValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(WalletValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(WalletAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(WalletAlreadyDeletedException e, HttpServletRequest request) {
        String error = "Wallet already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(WalletAlreadyExistsException.class)
    public ResponseEntity<ControllerException> alreadyExists(WalletAlreadyExistsException e, HttpServletRequest request) {
        String error = "Wallet already exists.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(WalletNotFoundException e, HttpServletRequest request) {
        String error = "Wallet not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

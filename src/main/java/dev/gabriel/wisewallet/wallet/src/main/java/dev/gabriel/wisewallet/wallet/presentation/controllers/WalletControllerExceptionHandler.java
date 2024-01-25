package dev.gabriel.wisewallet.wallet.presentation.controllers;

import dev.gabriel.wisewallet.core.presentation.exceptions.ControllerException;
import dev.gabriel.wisewallet.wallet.domain.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@SuppressWarnings("unused")
@RestControllerAdvice
public class WalletControllerExceptionHandler {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Validation failed.")
    @ExceptionHandler(WalletValidationException.class)
    public ResponseEntity<ControllerException> validationFailed(WalletValidationException e, HttpServletRequest request) {
        String error = "Validation failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already deleted.")
    @ExceptionHandler(WalletAlreadyDeletedException.class)
    public ResponseEntity<ControllerException> alreadyDeleted(WalletAlreadyDeletedException e, HttpServletRequest request) {
        String error = "Already deleted.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already exists.")
    @ExceptionHandler(WalletAlreadyExistsException.class)
    public ResponseEntity<ControllerException> alreadyExists(WalletAlreadyExistsException e, HttpServletRequest request) {
        String error = "Already exists.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found.")
    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ControllerException> notFound(WalletNotFoundException e, HttpServletRequest request) {
        String error = "Not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Reached max number.")
    @ExceptionHandler(ReachedMaxWalletsException.class)
    public ResponseEntity<ControllerException> reachedMaxNumber(ReachedMaxWalletsException e, HttpServletRequest request) {
        String error = "Reached max number.";
        HttpStatus status = HttpStatus.CONFLICT;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

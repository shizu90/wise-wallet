package dev.gabriel.wisewallet.auth.controllers;

import dev.gabriel.wisewallet.auth.services.AuthenticationFailedException;
import dev.gabriel.wisewallet.core.presentation.exceptions.ControllerException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@SuppressWarnings("unused")
@RestControllerAdvice
public class AuthControllerExceptionHandler {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ControllerException> authenticationFailed(AuthenticationFailedException e, HttpServletRequest request) {
        String error = "Authentication failed.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ControllerException exception = new ControllerException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(exception);
    }
}

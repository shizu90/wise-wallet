package dev.gabriel.wisewallet.auth.controllers;

import dev.gabriel.wisewallet.auth.dtos.AuthRequestDto;
import dev.gabriel.wisewallet.auth.dtos.AuthResponseDto;
import dev.gabriel.wisewallet.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/api/auth/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    private final AuthService service;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok().body(service.login(authRequestDto));
    }
}

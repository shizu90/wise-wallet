package dev.gabriel.wisewallet.user.presentation.controllers;

import dev.gabriel.wisewallet.user.presentation.dtos.UserRequestDto;
import dev.gabriel.wisewallet.user.infrastructure.services.UserService;
import dev.gabriel.wisewallet.user.presentation.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.getById(userId));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto request) {
        return ResponseEntity.ok().body(userService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID userId, @RequestBody UserRequestDto request) {
        request = new UserRequestDto(
                userId,
                request.name(),
                request.email(),
                request.password(),
                request.defaultCurrencyCode(),
                request.defaultLanguage()
        );
        return ResponseEntity.ok().body(userService.update(request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.delete(userId);

        return ResponseEntity.ok().body(null);
    }
}

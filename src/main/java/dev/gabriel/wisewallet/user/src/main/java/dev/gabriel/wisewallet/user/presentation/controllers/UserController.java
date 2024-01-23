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
    public ResponseEntity<UserResponseDto> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> newUser(@RequestBody UserRequestDto request) {
        return ResponseEntity.ok().body(userService.newUser(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> updateUserData(@PathVariable UUID id, @RequestBody UserRequestDto request) {
        request = new UserRequestDto(
                id,
                request.name(),
                request.email(),
                request.password(),
                request.defaultCurrencyCode(),
                request.defaultLanguage()
        );
        return ResponseEntity.ok().body(userService.updateUserData(request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        return ResponseEntity.ok().body(null);
    }
}

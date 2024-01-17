package dev.gabriel.wisewallet.user.presentation.controllers;

import dev.gabriel.wisewallet.user.infrastructure.services.UserService;
import dev.gabriel.wisewallet.user.presentation.dtos.UserRequestDto;
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
    public ResponseEntity<UserResponseDto> getById(@PathVariable String id) {
        UserResponseDto response = userService.getById(UUID.fromString(id));

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto request) {
        UserResponseDto response = userService.create(request);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String id, @RequestBody UserRequestDto request) {
        request = new UserRequestDto(
                UUID.fromString(id),
                request.name(),
                request.email(),
                request.password(),
                request.defaultCurrencyCode(),
                request.defaultLanguage()
        );
        UserResponseDto response = userService.update(request);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.delete(UUID.fromString(id));

        return ResponseEntity.ok().body(null);
    }
}

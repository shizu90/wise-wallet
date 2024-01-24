package dev.gabriel.wisewallet.user.presentation.dtos;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String name,
        String email,
        UserConfigurationDto configuration,
        Instant createdAt,
        Instant updatedAt
) {}

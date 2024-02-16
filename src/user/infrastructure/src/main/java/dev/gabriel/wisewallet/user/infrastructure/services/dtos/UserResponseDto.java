package dev.gabriel.wisewallet.user.infrastructure.services.dtos;

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

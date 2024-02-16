package dev.gabriel.wisewallet.category.infrastructure.services.dtos;

import java.time.Instant;
import java.util.UUID;

public record CategoryResponseDto(
        UUID id,
        String name,
        UUID userId,
        Instant createdAt,
        Instant updatedAt
) {}

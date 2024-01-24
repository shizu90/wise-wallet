package dev.gabriel.wisewallet.category.presentation.dtos;

import java.time.Instant;
import java.util.UUID;

public record CategoryResponseDto(
        UUID id,
        String name,
        UUID userId,
        Instant createdAt,
        Instant updatedAt
) {}

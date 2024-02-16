package dev.gabriel.wisewallet.category.infrastructure.services.dtos;

import java.util.UUID;

public record CategoryRequestDto(
        UUID id,
        String name,
        UUID userId
) {}

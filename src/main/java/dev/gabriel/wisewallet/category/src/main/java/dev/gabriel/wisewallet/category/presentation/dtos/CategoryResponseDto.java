package dev.gabriel.wisewallet.category.presentation.dtos;

import java.util.UUID;

public record CategoryResponseDto(
        UUID id,
        String name,
        UUID userId
) {}

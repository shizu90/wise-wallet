package dev.gabriel.wisewallet.category.presentation.dtos;

import java.util.UUID;

public record CategoryRequestDto(
        UUID id,
        String name,
        UUID userId
) {}

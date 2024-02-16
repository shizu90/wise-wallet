package dev.gabriel.wisewallet.auth.dtos;

import java.util.UUID;

public record AuthResponseDto(
        UUID id,
        String email,
        String token
) {}

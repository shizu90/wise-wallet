package dev.gabriel.wisewallet.auth.dtos;

import java.util.UUID;

public record AuthResponseDto(
        String email,
        String token
) {}

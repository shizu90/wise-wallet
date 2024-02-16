package dev.gabriel.wisewallet.user.infrastructure.services.dtos;

import java.util.UUID;

public record UserRequestDto (
        UUID id,
        String name,
        String email,
        String password,
        String defaultCurrencyCode,
        String defaultLanguage
) {}

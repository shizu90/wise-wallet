package dev.gabriel.wisewallet.user.presentation.dtos;

import java.util.UUID;

public record UserRequestDto (
        UUID id,
        String name,
        String email,
        String password,
        String defaultCurrencyCode,
        String defaultLanguage
) {}

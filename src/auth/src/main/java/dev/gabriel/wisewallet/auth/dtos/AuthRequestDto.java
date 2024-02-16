package dev.gabriel.wisewallet.auth.dtos;

public record AuthRequestDto(
        String username,
        String email,
        String password,
        String defaultCurrencyCode,
        String defaultLanguage
) {}
